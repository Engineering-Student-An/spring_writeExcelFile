package study.excelwrite;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class WriteExcel {
    public static void writeToExcel(String id, String name) {
        try {
            // 파일을 읽기 위한 InputStream 생성
            InputStream file = new FileInputStream("src/main/resources/static/student.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 새로운 행을 추가하기 위한 로직
            int lastRowNum = sheet.getLastRowNum();
            XSSFRow row = sheet.createRow(lastRowNum + 1);

            // 셀에 데이터 쓰기
            row.createCell(0).setCellValue(Integer.parseInt(id));
            row.createCell(1).setCellValue(name);

            // 변경 사항을 적용하기 위해 파일을 새로 쓰기
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/static/student.xlsx");
            workbook.write(fileOut);

            // 리소스 정리
            fileOut.close();
            workbook.close();
            file.close(); // InputStream도 닫아주는 것이 좋습니다.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 실행 결과 확인
        // 번호 : 1 ~ 10 , 이름 : username1 ~ username10
        for (int i = 1; i <= 10; i++) {
            writeToExcel(Integer.toString(i), "username" + i);
        }
    }
}