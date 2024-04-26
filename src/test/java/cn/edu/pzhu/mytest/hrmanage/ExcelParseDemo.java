package cn.edu.pzhu.mytest.hrmanage;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangcz
 * @since 20240425
 */
public class ExcelParseDemo {

    public String path = "/Users/hfy/Desktop/2024ka.xlsx";

    public static void main(String[] args) {
        ExcelParseDemo demo = new ExcelParseDemo();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.addAll(demo.parse202401());
        employeeList.addAll(demo.parse202402("202402"));
        employeeList.addAll(demo.parse202402("202403"));

        String sql = demo.generateSQL(employeeList);
        System.out.println(sql);
    }

    private String generateSQL(List<Employee> employeeList) {
        StringBuilder sb = new StringBuilder();
        String sqlTemplate = "INSERT INTO hr_sales_commission"
                + "(original_id, contract_id, contract_no, contract_sign_date, commission_user_id, "
                + "commission_true_id, sales_dist_paid_amount, person_commission_ratio, after_deducted_amount, "
                + "receipt_date, creator_id, modifier_id) VALUES"
                + "('%s', '%s', '%s', '%s', '%s', "
                + "'%s', %s, 100.00, %s, "
                + "'%s','zhangcz20240425', 'zhangcz20240425');\n";
        for (Employee employee : employeeList) {
            String sql = String.format(sqlTemplate,
                    employee.getId(), employee.getId(), employee.getId(), employee.getDate(), employee.getUserId(),
                    employee.getTrueId(), employee.getSalesDistPaidAmount(), employee.getAfterDeductAmount(), employee.getDate());
            sb.append(sql);
        }

        return sb.toString();
    }

    public List<Employee> parse202401() {
        List<Employee> employeeList = parseSheetForCommission(2);
        for (Employee employee : employeeList) {
            employee.setDate(formatDate("202401"));
        }
        return employeeList;
    }

    public List<Employee> parse202402(String month) {
        int stairIndex = 3;
        int commissionIndex = 4;
        if (month.equals("202403")) {
            stairIndex = 5;
            commissionIndex = 6;
        }

        List<Employee> stairEmployeeList = parseSheetForStair(stairIndex);
        List<Employee> commissionEmployeeList = parseSheetForCommission(commissionIndex);
        Map<String, Employee> commissionMap = commissionEmployeeList.stream().collect(Collectors.toMap(Employee::getUserId, Function.identity()));

        List<Employee> mergeEmployeeList = new ArrayList<>();
        for (Employee stair : stairEmployeeList) {
            Employee commission = commissionMap.get(stair.getUserId());
            mergeEmployeeList.add(stair);
            if (commission == null) {
                continue;
            }

            stair.setAfterDeductAmount(commission.getAfterDeductAmount());
            commissionMap.remove(stair.getUserId());
        }

        if (!commissionMap.isEmpty()) {
            mergeEmployeeList.addAll(commissionMap.values());
        }

        for (Employee employee : mergeEmployeeList) {
            employee.setDate(formatDate(month));
        }

        return mergeEmployeeList;
    }

    @SneakyThrows
    private List<Employee> parseSheetForStair(int sheetIndex) {
        List<Employee> employeeList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
        int lastRowNumber = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNumber; i++) {
            XSSFRow row = sheet.getRow(i);
            String amount = row.getCell(2).getRawValue();
            String userId = row.getCell(3).getStringCellValue();
            String trueId = row.getCell(4).getStringCellValue();

            Employee employee = new Employee();
            employee.setId(generateUuid());
            employee.setSalesDistPaidAmount(new BigDecimal(amount));
            employee.setTrueId(trueId);
            employee.setUserId(userId);
            employeeList.add(employee);
        }

        return employeeList;
    }

    @SneakyThrows
    private List<Employee> parseSheetForCommission(int sheetIndex) {
        List<Employee> employeeList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
        int lastRowNumber = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNumber; i++) {
            XSSFRow row = sheet.getRow(i);
            String userId = row.getCell(2).getStringCellValue();
            String trueId = row.getCell(3).getStringCellValue();
            String amount = row.getCell(6).getRawValue();

            Employee employee = new Employee();
            employee.setId(generateUuid());
            employee.setAfterDeductAmount(new BigDecimal(amount));
            employee.setTrueId(trueId);
            employee.setUserId(userId);
            employeeList.add(employee);
        }

        return employeeList;
    }

    private String formatDate(String month) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMM");
        DateTime dateTime = dateTimeFormatter.parseDateTime(month);
        return dateTime.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
    }

    public String generateUuid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", StringUtils.EMPTY);
    }
}
