import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WordToMySql {

    public static void main(String[] args) throws IOException {
        // Открыть документ Word и найти таблицу
        XWPFDocument document = new XWPFDocument(new FileInputStream("listUsersAVKWORD_new.docx"));
        XWPFTable table = document.getTables().get(0);

        // Получить заголовки столбцов таблицы
        List<XWPFTableRow> rows = table.getRows();
        XWPFTableRow headerRow = rows.get(0);
        List<String> headers = headerRow.getTableCells().stream()
                .map(cell -> cell.getText())
                .collect(Collectors.toList());

        // Создать SQL-скрипт INSERT для каждой строки в таблице
        StringBuilder sqlBuilder = new StringBuilder();
        for (int i = 1; i < rows.size(); i++) {
            XWPFTableRow row = rows.get(i);
            List<String> values = row.getTableCells().stream()
                    .map(cell -> cell.getText())
                    .collect(Collectors.toList());

            // Создать SQL-запрос INSERT
            String sql = "INSERT INTO User (" + String.join(",", headers) + ") "
                    + "VALUES ('" + String.join("','", values) + "');\n";
            sqlBuilder.append(sql);
        }

        // Сохранить SQL-скрипт в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultScriptAVK.sql"))) {
            writer.write(sqlBuilder.toString());
        }
    }
}
