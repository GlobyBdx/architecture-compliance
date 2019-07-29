/*
 * Copyright 2019 Benoit Faget. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * @author Benoit Faget
 * @version 0.1.0
 */

package fr.ubordeaux.ddd.plugin.description;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public final class JsonDescriptionImporter extends AbstractDescriptionImporter {
    public JsonDescriptionImporter(Description description, Syntax syntax) {
        super(description, syntax);
    }

    public void importDescriptionFromClasses(File file) {
        try {
            JSONObject jsonArchitecture = (JSONObject)new JSONParser().parse(new FileReader(file));
            this.importClasses((JSONObject)jsonArchitecture.get("classes"));
            this.importPackagesFromClasses();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void importDescriptionFromPackages(File file) {
        try {
            JSONObject jsonArchitecture = (JSONObject)new JSONParser().parse(new FileReader(file));
            this.importPackages((JSONObject)jsonArchitecture.get("packages"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void importPackages(JSONObject jsonPackages) {
        for (Object key : jsonPackages.keySet()) {
            JSONObject jsonPackage = (JSONObject)jsonPackages.get(key);
            this.importAnnotations((String)key, (JSONArray)jsonPackage.get("annotations"), this.getPackages());
            this.importClasses((JSONObject)(jsonPackage).get("classes"));
        }
    }

    private void importClasses(JSONObject jsonClasses) {
        for (Object key : jsonClasses.keySet()) {
            JSONObject jsonClass = (JSONObject)jsonClasses.get(key);
            this.importAnnotations((String)key, (JSONArray)jsonClass.get("annotations"), this.getClasses());
            this.importFields((JSONObject)jsonClass.get("fields"));
            this.importMethods((JSONObject)jsonClass.get("methods"));
        }
    }

    private void importFields(JSONObject jsonFields) {
        for (Object key : jsonFields.keySet()) {
            JSONObject jsonField = (JSONObject)jsonFields.get(key);
            this.importAnnotations((String)key, (JSONArray)jsonField.get("annotations"), this.getFields());
        }
    }

    private void importMethods(JSONObject jsonMethods) {
        for (Object key : jsonMethods.keySet()) {
            JSONObject jsonMethod = (JSONObject)jsonMethods.get(key);
            this.importAnnotations((String)key, (JSONArray)jsonMethod.get("annotations"), this.getMethods());
        }
    }

    @SuppressWarnings("unchecked")
    private void importAnnotations(String name, JSONArray jsonAnnotations, Map<String, List<String>> map) {
        Iterator<String> jsonAnnotationsIterator = jsonAnnotations.iterator();
        while (jsonAnnotationsIterator.hasNext()) {
            String annotation = jsonAnnotationsIterator.next();
            if (!map.containsKey(annotation)) {
                map.put(annotation, new ArrayList<>());
            }
            map.get(annotation).add(name);
        }
    }
}
