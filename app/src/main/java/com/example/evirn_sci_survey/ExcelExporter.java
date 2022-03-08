package com.example.evirn_sci_survey;

import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.evirn_sci_survey.database.Answer;
import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class ExcelExporter {

    private static final String TAG = "ExcelExporter";

    /**
     * Writes an excel file to an SD card
     *
     *
     * @param questionDao
     * @param questionAnswerDao
     * @param answerDao
     * @param activeSurvey
     * @return String containing if the excel file write was successful or not
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public static String export(SurveyQuestionDao questionDao, SurveyQuestionAnswerDao questionAnswerDao, AnswerDao answerDao, int activeSurvey) {

        Log.i(TAG, "Excel Exporter has executed");

        HSSFWorkbook workbook = new HSSFWorkbook(); // Added this string here
        List<SurveyQuestion> questions = questionDao.getAllQuestionsInSurvey(activeSurvey);
        for(int i = 0; i < questions.size(); i++) {
            SurveyQuestion question = questions.get(i);
            HSSFSheet sheet = workbook.createSheet("Question " + i);
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(question.getQuestionText());

            row = sheet.getRow(1);
            if(row == null) {
                row = sheet.createRow(1);
            }
            List<SurveyQuestionAnswer> questionAnswers = questionAnswerDao.getAnswersInQuestion(activeSurvey, question.getQuestionId());
            for(int j = 0; j < questionAnswers.size(); j++) {
                SurveyQuestionAnswer questionAnswer = questionAnswers.get(j);
                cell = row.getCell(j);
                if(cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellValue(questionAnswer.getMofferedAnsText());

                List<Answer> answers = answerDao.getAnswersFromQuestionAnswer(activeSurvey, questionAnswer.getMofferedAnsId());
                for(int k = 0; k < answers.size(); k++) {
                    Answer answer = answers.get(k);
                    HSSFRow row2 = sheet.getRow(k+2);
                    if(row2 == null) {
                        row2 = sheet.createRow(k+2);
                    }
                    HSSFCell cell2 = row2.getCell(j);
                    if(cell2 == null) {
                        cell2 = row2.createCell(j);
                    }
                    String questionType = questionAnswer.getQuestionType();
                    if(questionType == null) {questionType = "";}
                    switch (questionType) {
                        case "Checkbox":
                            if(answer.isCheckboxValue()) {
                                cell2.setCellValue("True");
                            } else {
                                cell2.setCellValue("False");
                            }
                            break;
                        case "Slider":
                            cell2.setCellValue(String.valueOf(answer.getSliderValue()));
                            break;
                        default:
                            cell2.setCellValue(answer.getManotherAnsText());
                            break;
                    }
                }
            }
        }

        // Get the directory of the storage card
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File filePath = new File(baseDir + "/Survey Data.xls");

        String result = ""; // Result string
        try {
            if(!filePath.exists()) { filePath.createNewFile(); }
            FileOutputStream outputStream = new FileOutputStream(filePath); // Writing the Excel file to path
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            result = "File Created At: " + filePath.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Error writing to file";
        }

        // Returns a
        return result;
    }
}
