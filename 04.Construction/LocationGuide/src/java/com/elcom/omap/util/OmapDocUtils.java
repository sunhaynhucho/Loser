/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import org.apache.poi.hwpf.usermodel.HeaderStories;

import java.io.*;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author SonDV
 */
public class OmapDocUtils {

    public static void main(String[] args) {
        /**
         * This is the document that you want to read using Java.*
         */
        String fileName = "C:\\Users\\SonDV\\Downloads\\abc.doc";

        /**
         * Method call to read the document (demonstrate some useage of POI)*
         */
        readMyDocument(fileName);

    }

    public static String readDocxFile(String fileName) {
        String result = "";
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                result += para.getText();
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public static String readMyDocument(String fileName) {
        POIFSFileSystem fs = null;
        String result = "";
        try {
            fs = new POIFSFileSystem(new FileInputStream(fileName));
            HWPFDocument doc = new HWPFDocument(fs);

            /**
             * Read the content *
             */
            result = readParagraphs(doc);

            // int pageNumber=1;

            /**
             * We will try reading the header for page 1*
             */
            // readHeader(doc, pageNumber);
            /**
             * Let's try reading the footer for page 1*
             */
            //readFooter(doc, pageNumber);
            /**
             * Read the document summary*
             */
            // readDocumentSummary(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String readParagraphs(HWPFDocument doc) throws Exception {
        String result = "";
        WordExtractor we = new WordExtractor(doc);

        /**
         * Get the total number of paragraphs*
         */
        String[] paragraphs = we.getParagraphText();
        //  System.out.println("Total Paragraphs: "+paragraphs.length);

        for (int i = 0; i < paragraphs.length; i++) {
            result += paragraphs[i].toString();
            // System.out.println("Length of paragraph "+(i +1)+": "+ paragraphs[i].length());
            // System.out.println(paragraphs[i].toString());

        }
        return result;
    }

    public static void readHeader(HWPFDocument doc, int pageNumber) {
        HeaderStories headerStore = new HeaderStories(doc);
        String header = headerStore.getHeader(pageNumber);
        System.out.println("Header Is: " + header);

    }

    public static void readFooter(HWPFDocument doc, int pageNumber) {
        HeaderStories headerStore = new HeaderStories(doc);
        String footer = headerStore.getFooter(pageNumber);
        System.out.println("Footer Is: " + footer);

    }

    public static void readDocumentSummary(HWPFDocument doc) {
        DocumentSummaryInformation summaryInfo = doc.getDocumentSummaryInformation();
        String category = summaryInfo.getCategory();
        String company = summaryInfo.getCompany();
        int lineCount = summaryInfo.getLineCount();
        int sectionCount = summaryInfo.getSectionCount();
        int slideCount = summaryInfo.getSlideCount();

        System.out.println("---------------------------");
        System.out.println("Category: " + category);
        System.out.println("Company: " + company);
        System.out.println("Line Count: " + lineCount);
        System.out.println("Section Count: " + sectionCount);
        System.out.println("Slide Count: " + slideCount);

    }
}
