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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public final class XmlDescriptionImporter extends AbstractDescriptionImporter {
    public XmlDescriptionImporter(Description description, Syntax syntax) {
        super(description, syntax);
    }

    public void importDescriptionFromClasses(File file) {
        try {
            this.importClasses(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(file).getDocumentElement().getChildNodes());
            this.importPackagesFromClasses();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void importDescriptionFromPackages(File file) {
        try {
            this.importPackages(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(file).getDocumentElement().getChildNodes());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void importPackages(NodeList xmlPackages) {
        for (int i = 0; i < xmlPackages.getLength(); ++i) {
            if (xmlPackages.item(i).getNodeType() == Node.ELEMENT_NODE && xmlPackages.item(i).hasChildNodes()) {
                Element xmlPackage = (Element)xmlPackages.item(i);
                this.importAnnotations(
                        this.getElementChildNodeTextContent(xmlPackage, "name"),
                        this.getElementChildNodes(xmlPackage, "annotations"), this.getPackages());
                this.importClasses(this.getElementChildNodes(xmlPackage, "classes"));
            }
        }
    }

    private void importClasses(NodeList xmlClasses) {
        for (int i = 0; i < xmlClasses.getLength(); ++i) {
            if (xmlClasses.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element xmlClass = (Element)xmlClasses.item(i);
                this.importAnnotations(
                        this.getElementChildNodeTextContent(xmlClass, "name"),
                        this.getElementChildNodes(xmlClass, "annotations"), this.getClasses());
                this.importFields(this.getElementChildNodes(xmlClass, "fields"));
                this.importMethods(this.getElementChildNodes(xmlClass, "methods"));
            }
        }
    }

    private void importFields(NodeList xmlFields) {
        for (int i = 0; i < xmlFields.getLength(); ++i) {
            if (xmlFields.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element xmlField = (Element)xmlFields.item(i);
                this.importAnnotations(
                        this.getElementChildNodeTextContent(xmlField, "name"),
                        this.getElementChildNodes(xmlField, "annotations"), this.getFields());
            }
        }
    }

    private void importMethods(NodeList xmlMethods) {
        for (int i = 0; i < xmlMethods.getLength(); ++i) {
            if (xmlMethods.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element xmlMethod = (Element)xmlMethods.item(i);
                this.importAnnotations(
                        this.getElementChildNodeTextContent(xmlMethod, "name"),
                        this.getElementChildNodes(xmlMethod, "annotations"), this.getMethods());
            }
        }
    }

    private void importAnnotations(String name, NodeList xmlAnnotations, Map<String, List<String>> map) {
        for (int i = 0; i < xmlAnnotations.getLength(); ++i) {
            if (xmlAnnotations.item(i).getNodeType() == Node.ELEMENT_NODE) {
                String annotation = xmlAnnotations.item(i).getTextContent();
                if (!map.containsKey(annotation)) {
                    map.put(annotation, new ArrayList<>());
                }
                map.get(annotation).add(name);
            }
        }
    }

    private String getElementChildNodeTextContent(Element element, String tag) {
        return this.getElementChildNodes(element, tag).item(0).getTextContent();
    }

    private NodeList getElementChildNodes(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getChildNodes();
    }
}
