package com.utils.commonUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 读 Excel 文件工具类
 */
public class ExcelUtil {

    //private static final int NEW = 2007;
    private static final int OLD = 2003;
    private int version;
    private Object workbook;
    private Object sheet;
    private Object row;
    private Object cell;
    private List<String> list;

    /**
     * 举个栗子, 运行以下代码控制台会打印如下代码:<br>
     * 4<br>
     * 5<br>
     * 6<br>
     * 7<br>
     * ....
     */
    public void demo() throws Exception {
        File file =new File("test.xls"); //只是举例
        String fileName=file.getName();
        //要想在getList方法内使用外部变量, 必须声明成长度为 1 的数组形式
        final String[] index = {""};
        //重点来了!!
        long runTime = ExcelUtil.getLists(fileName, new ExcelUtil.Lists() {
            @Override
            public void getList(List<String> list) {
                index[0] = list.get(0);
                System.out.println(index[0]);
            }
        }, 3); //指定了表头为3行
    }

    /**
     * 读 Excel 文件到 List, 不指定表头行数
     * @param path 文件路径
     * @param lists 匿名回调函数, 格式如下:<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;new ExcelUtil.Lists() {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void getList(List<String> list) {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//内容<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;}
     * @return 执行时间 (毫秒)
     */
    public static long getLists(String path, Lists lists) throws Exception {
        return new ExcelUtil(path).getLists(lists, 0);
    }

    /**
     * 读 Excel 文件到 List, 指定表头行数
     * @param path 文件路径
     * @param lists 匿名回调函数, 格式如下:<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;new ExcelUtil.Lists() {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void getList(List<String> list) {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//内容<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;}
     * @param header 表头行数
     * @return 执行时间 (毫秒)
     */
    public static long getLists(String path, Lists lists, int header) throws Exception {
        return new ExcelUtil(path).getLists(lists, 0);
    }

    /**
     * 读 Excel 文件到 List, 不指定表头行数
     * @param file MultipartFile文件
     * @param lists 匿名回调函数, 格式如下:<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;new ExcelUtil.Lists() {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void getList(List<String> list) {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//内容<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;}
     * @return 执行时间 (毫秒)
     */
    public static long getLists(MultipartFile file, Lists lists) throws Exception {
        return new ExcelUtil(file).getLists(lists, 0);
    }

    /**
     * 读 Excel 文件到 List, 指定表头行数
     * @param file MultipartFile文件
     * @param lists 匿名回调函数, 格式如下:<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;new ExcelUtil.Lists() {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void getList(List<String> list) {<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//内容<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
     *              &nbsp;&nbsp;&nbsp;&nbsp;}
     * @param header 表头行数
     * @return 执行时间 (毫秒)
     */
    public static long getLists(MultipartFile file, Lists lists, int header) throws Exception {
        return new ExcelUtil(file).getLists(lists, header);
    }

    /**
     * 供 getLists 回调的函数, 需要实现一个方法
     */
    public interface Lists {
        /**
         * 遍历 lists 得到每一个 list
         */
        void getList(List<String> list);
    }

    private long getLists(Lists lists, int header) {
        long startTime = System.currentTimeMillis();
        long index = 0;
        for(int i = 0; i < getSheetSize(); i++) {
            for(int j = header; j < getRowSize(i); j++) {
                list.clear();
                list.add(Long.toString(++index));
                for(short k = 0; k < getCellSize(i ,j); k++) {
                    String value = get(i, j, k);
                    list.add(value);
                }
                lists.getList(list);
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private ExcelUtil(String path) throws Exception {
        String prefix = path.substring(path.lastIndexOf(".")+1);
        switch (prefix) {
            //case "xlsx":
            //    this.workbook = new XSSFWorkbook(new FileInputStream(path));
            //    this.version =NEW;
            //    break;
            case "xls":
                this.workbook = new HSSFWorkbook(new FileInputStream(path));
                this.version = OLD;
                break;
            default:
                throw new Exception("文件格式不正确!");
        }
        this.list = new ArrayList<>();
    }

    private ExcelUtil(MultipartFile file) throws Exception {
        char end = file.getOriginalFilename().charAt(file.getOriginalFilename().length() - 1);
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
        switch (prefix) {
            //case "xlsx":
            //    this.workbook = new XSSFWorkbook(file.getInputStream());
            //    this.version =NEW;
            //    break;
            case "xls":
                this.workbook = new HSSFWorkbook(file.getInputStream());
                this.version = OLD;
                break;
            default:
                throw new Exception("文件格式不正确!");
        }
        this.list = new ArrayList<>();
    }

    private String get(int sheet, int row, short cell) {
        Object value = "";
        switch (this.version) {
            //case NEW :
            //    if(this.workbook != null) {
            //        this.sheet = ((XSSFWorkbook) this.workbook).getSheetAt(sheet);
            //        if(this.sheet != null) {
            //            this.row = ((XSSFSheet) this.sheet).getRow(row);
            //            if(this.row != null) {
            //                this.cell = ((XSSFRow) this.row).getCell(cell);
            //                if(this.cell != null) {
            //                    switch (((XSSFCell) this.cell).getCellType()) {
            //                        case XSSFCell.CELL_TYPE_NUMERIC:
            //                            value = (((XSSFCell) this.cell).getNumericCellValue());
            //                            break;
            //                        case XSSFCell.CELL_TYPE_BOOLEAN:
            //                            value = ((XSSFCell) this.cell).getBooleanCellValue();
            //                            break;
            //                        default:
            //                            value = ((XSSFCell) this.cell).getStringCellValue();
            //                    }
            //                }
            //            }
            //        }
            //    }
            //    break;
            default:
                if(this.workbook != null) {
                    this.sheet = ((HSSFWorkbook) this.workbook).getSheetAt(sheet);
                    if(this.sheet != null) {
                        this.row = ((HSSFSheet) this.sheet).getRow(row);
                        if(this.row != null) {
                            this.cell = ((HSSFRow) this.row).getCell(cell);
                            if(this.cell != null) {
                                switch (((HSSFCell) this.cell).getCellType()) {
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        value = (((HSSFCell) this.cell).getNumericCellValue());
                                        break;
                                    case HSSFCell.CELL_TYPE_BOOLEAN:
                                        value = ((HSSFCell) this.cell).getBooleanCellValue();
                                        break;
                                    default:
                                        value = ((HSSFCell) this.cell).getStringCellValue();
                                }
                            }
                        }
                    }
                }
        }
        return value.toString().trim();
    }

    private int getSheetSize() {
        switch (this.version) {
            //case NEW :
            //    return ((XSSFWorkbook) this.workbook).getNumberOfSheets();
            default:
                return ((HSSFWorkbook) this.workbook).getNumberOfSheets();
        }
    }

    private int getRowSize(int sheet) {
        switch (this.version) {
            //case NEW :
            //    this.sheet = ((XSSFWorkbook) this.workbook).getSheetAt(sheet);
            //    return ((XSSFSheet) this.sheet).getLastRowNum() + 1;
            default:
                this.sheet = ((HSSFWorkbook) this.workbook).getSheetAt(sheet);
                return ((HSSFSheet) this.sheet).getLastRowNum() + 1;
        }
    }

    private int getCellSize(int sheet, int row) {
        switch (this.version) {
            //case NEW :
            //    this.sheet = ((XSSFWorkbook) this.workbook).getSheetAt(sheet);
            //    this.row = ((XSSFSheet) this.sheet).getRow(row);
            //    return ((XSSFRow) this.row).getLastCellNum();
            default:
                this.sheet = ((HSSFWorkbook) this.workbook).getSheetAt(sheet);
                this.row = ((HSSFSheet) this.sheet).getRow(row);
                return ((HSSFRow) this.row).getLastCellNum();
        }
    }

}
