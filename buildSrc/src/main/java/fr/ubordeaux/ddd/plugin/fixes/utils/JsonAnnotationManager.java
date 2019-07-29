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

package fr.ubordeaux.ddd.plugin.fixes.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import fr.ubordeaux.ddd.plugin.description.Description;

public final class JsonAnnotationManager extends AbstractAnnotationManager {
    public JsonAnnotationManager(Description description) {
        super(description);
    }

    public void manageArchitectureFromClasses(File file, String name, String annotation, boolean inclusion) {
        try {
            JSONObject jsonArchitecture = (JSONObject)new JSONParser().parse(new FileReader(file));
            this.manageClassAnnotation(name, annotation, (JSONObject)jsonArchitecture.get("classes"), inclusion);
            this.updateAsJson(file, jsonArchitecture);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void manageArchitectureFromPackages(File file, String name, String annotation, boolean inclusion) {
        try {
            JSONObject jsonArchitecture = (JSONObject)new JSONParser().parse(new FileReader(file));
            this.managePackageAnnotation(name, annotation, (JSONObject)jsonArchitecture.get("packages"), inclusion);
            this.updateAsJson(file, jsonArchitecture);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateAsJson(File file, JSONObject jsonObject) {
        try {
            Writer writer = new FileWriter(file);
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(
                    new JsonParser().parse(jsonObject.toJSONString())));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void managePackageAnnotation(String name, String annotation, JSONObject jsonPackages, boolean inclusion) {
        for (Object key : jsonPackages.keySet()) {
            JSONObject jsonPackage = (JSONObject)jsonPackages.get(key);
            if (name.compareTo((String)key) == 0) {
                if (!inclusion) {
                    this.removeAnnotation(annotation, (JSONArray)jsonPackage.get("annotations"));
                }
                else {
                    ((JSONArray)jsonPackage.get("annotations")).add(annotation);
                }
                return;
            }
            if (name.startsWith((String)key)) {
                this.manageClassAnnotation(name, annotation, (JSONObject)jsonPackage.get("classes"), inclusion);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void manageClassAnnotation(String name, String annotation, JSONObject jsonClasses, boolean inclusion) {
        for (Object key : jsonClasses.keySet()) {
            JSONObject jsonClass = (JSONObject)jsonClasses.get(key);
            if (name.compareTo((String)key) == 0) {
                if (!inclusion) {
                    this.removeAnnotation(annotation, (JSONArray)jsonClass.get("annotations"));
                }
                else {
                    ((JSONArray)jsonClass.get("annotations")).add(annotation);
                }
                return;
            }
            if (name.startsWith((String)key)) {
                this.manageFieldAnnotation(name, annotation, (JSONObject)jsonClass.get("fields"), inclusion);
                this.manageMethodAnnotation(name, annotation, (JSONObject)jsonClass.get("methods"), inclusion);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void manageFieldAnnotation(String name, String annotation, JSONObject jsonFields, boolean inclusion) {
        for (Object key : jsonFields.keySet()) {
            JSONObject jsonField = (JSONObject)jsonFields.get(key);
            if (name.compareTo((String)key) == 0) {
                if (!inclusion) {
                    this.removeAnnotation(annotation, (JSONArray)jsonField.get("annotations"));
                }
                else {
                    ((JSONArray)jsonField.get("annotations")).add(annotation);
                }
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void manageMethodAnnotation(String name, String annotation, JSONObject jsonMethods, boolean inclusion) {
        for (Object key : jsonMethods.keySet()) {
            JSONObject jsonMethod = (JSONObject)jsonMethods.get(key);
            if (name.compareTo((String)key) == 0) {
                if (!inclusion) {
                    this.removeAnnotation(annotation, (JSONArray)jsonMethod.get("annotations"));
                }
                else {
                    ((JSONArray)jsonMethod.get("annotations")).add(annotation);
                }
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void removeAnnotation(String annotation, JSONArray jsonAnnotations) {
        int index = 0;
        Iterator<String> jsonAnnotationsIterator = jsonAnnotations.iterator();
        while (jsonAnnotationsIterator.hasNext() && annotation.compareTo(jsonAnnotationsIterator.next()) != 0) {
            index++;
        }
        jsonAnnotations.remove(index);
    }
}
