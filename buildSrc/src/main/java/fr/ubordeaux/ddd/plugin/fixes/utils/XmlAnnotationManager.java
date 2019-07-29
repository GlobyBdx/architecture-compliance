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
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.ubordeaux.ddd.plugin.description.Description;

public final class XmlAnnotationManager extends AbstractAnnotationManager {
    public XmlAnnotationManager(Description description) {
        super(description);
    }

    public void manageArchitectureFromClasses(File file, String name, String annotation, boolean inclusion) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            this.manageClassAnnotation(
                    document, name, annotation, document.getDocumentElement().getChildNodes(), inclusion);
            this.updateAsXml(file, document);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void manageArchitectureFromPackages(File file, String name, String annotation, boolean inclusion) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            this.managePackageAnnotation(
                    document, name, annotation, document.getDocumentElement().getChildNodes(), inclusion);
            this.updateAsXml(file, document);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void updateAsXml(File file, Document document) {
        try {
            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(document), new StreamResult(file));
        } catch (TransformerException | TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }
    }

    private void managePackageAnnotation(Document document, String name, String annotation,
            NodeList xmlPackages, boolean inclusion) {
        for (int i = 0; i < xmlPackages.getLength(); ++i) {
            if (xmlPackages.item(i).getNodeType() == Node.ELEMENT_NODE && xmlPackages.item(i).hasChildNodes()) {
                Element xmlPackage = (Element)xmlPackages.item(i);
                if (name.compareTo(this.getElementChildNodeTextContent(xmlPackage, "name")) == 0) {
                    if (!inclusion) {
                        this.removeAnnotation(annotation, xmlPackage);
                    }
                    else {
                        xmlPackage.getElementsByTagName("annotations").item(0).appendChild(
                                this.createTextNodeElement(document, "annotation", annotation));
                    }
                    return;
                }
                if (name.startsWith(this.getElementChildNodeTextContent(xmlPackage, "name"))) {
                    this.manageClassAnnotation(
                            document, name, annotation, this.getElementChildNodes(xmlPackage, "classes"), inclusion);
                }
            }
        }
    }

    private void manageClassAnnotation(Document document, String name, String annotation,
            NodeList xmlClasses, boolean inclusion) {
        for (int i = 0; i < xmlClasses.getLength(); ++i) {
            if (xmlClasses.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element xmlClass = (Element)xmlClasses.item(i);
                if (name.compareTo(this.getElementChildNodeTextContent(xmlClass, "name")) == 0) {
                    if (!inclusion) {
                        this.removeAnnotation(annotation, xmlClass);
                    }
                    else {
                        xmlClass.getElementsByTagName("annotations").item(0).appendChild(
                                this.createTextNodeElement(document, "annotation", annotation));
                    }
                    return;
                }
                if (name.startsWith(this.getElementChildNodeTextContent(xmlClass, "name"))) {
                    this.manageFieldAnnotation(
                            document, name, annotation, this.getElementChildNodes(xmlClass, "fields"), inclusion);
                    this.manageMethodAnnotation(
                            document, name, annotation, this.getElementChildNodes(xmlClass, "methods"), inclusion);
                }
            }
        }
    }

    private void manageFieldAnnotation(Document document, String name, String annotation,
            NodeList xmlFields, boolean inclusion) {
        for (int i = 0; i < xmlFields.getLength(); ++i) {
            if (xmlFields.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element xmlField = (Element)xmlFields.item(i);
                if (name.compareTo(this.getElementChildNodeTextContent(xmlField, "name")) == 0) {
                    if (!inclusion) {
                        this.removeAnnotation(annotation, xmlField);
                    }
                    else {
                        xmlField.getElementsByTagName("annotations").item(0).appendChild(
                                this.createTextNodeElement(document, "annotation", annotation));
                    }
                    break;
                }
            }
        }
    }

    private void manageMethodAnnotation(Document document, String name, String annotation,
            NodeList xmlMethods, boolean inclusion) {
        for (int i = 0; i < xmlMethods.getLength(); ++i) {
            if (xmlMethods.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element xmlMethod = (Element)xmlMethods.item(i);
                if (name.compareTo(this.getElementChildNodeTextContent(xmlMethod, "name")) == 0) {
                    if (!inclusion) {
                        this.removeAnnotation(annotation, xmlMethod);
                    }
                    else {
                        xmlMethod.getElementsByTagName("annotations").item(0).appendChild(
                                this.createTextNodeElement(document, "annotation", annotation));
                    }
                    break;
                }
            }
        }
    }

    private void removeAnnotation(String annotation, Element element) {
        NodeList xmlAnnotations = getElementChildNodes(element, "annotations");
        for (int i = 0; i < xmlAnnotations.getLength(); ++i) {
            if (xmlAnnotations.item(i).getNodeType() == Node.ELEMENT_NODE
                    && annotation.compareTo(xmlAnnotations.item(i).getTextContent()) == 0) {
                element.getElementsByTagName("annotations").item(0).removeChild(xmlAnnotations.item(i));
                break;
            }
        }
    }

    private Element createTextNodeElement(Document document, String name, String text) {
        Element element = document.createElement(name);
        Node node = document.createTextNode(text);
        element.appendChild(node);
        return element;
    }

    private String getElementChildNodeTextContent(Element element, String tag) {
        return this.getElementChildNodes(element, tag).item(0).getTextContent();
    }

    private NodeList getElementChildNodes(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getChildNodes();
    }
}
