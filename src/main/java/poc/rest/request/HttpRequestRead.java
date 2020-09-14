package poc.rest.request;

import poc.rest.config.configparcer.Column;
import poc.rest.config.configparcer.read.Join;
import poc.rest.request.parameters.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class HttpRequestRead{

    private String mapping = "";
    private String mappingWithCurlyBraces = "";


    private List<Column> selectColumns;
    private List<String> selectColumnsInQuotes = new ArrayList<>();

    private List<Join> joins;

    private List<Column> conditionColumns;
    private List<String> conditionColumnsInQuotes = new ArrayList<>();

    private List<RequestParam> requestParams = new ArrayList<>();

    public HttpRequestRead(List<Column> selectColumns, List<Join> joins, List<Column> conditionColumns){

        this.selectColumns = selectColumns;
        this.joins = joins;
        this.conditionColumns = conditionColumns;

        for(Column selectColumn : selectColumns)
            selectColumnsInQuotes.add(selectColumn.toStringQuotes());

        for(Column conditionColumn : conditionColumns)
            conditionColumnsInQuotes.add(conditionColumn.toStringQuotes());

        fillMapping();
        fillRequestParam();
    }




    public String getMapping() {
        return mapping;
    }

    public String getMappingWithCurlyBraces() {
        return mappingWithCurlyBraces;
    }

    public List<RequestParam> getRequestParams() {
        return requestParams;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public List<Column> getConditionColumns(){
        return conditionColumns;
    }

    public List<Column> getSelectColumns() {
        return selectColumns;
    }

    public List<String> getConditionColumnsInQuotes() {
        return conditionColumnsInQuotes;
    }

    public List<String> getSelectColumnsInQuotes() {
        return selectColumnsInQuotes;
    }




    //        /order.id/:order.id/user.name/:user.name
    private void fillMapping(){
        for(Column column : conditionColumns){
            mapping += "/" + column.toString() + "/:" + column.toString();
        }

        for(Column column : conditionColumns){
            mappingWithCurlyBraces += "/" + column.toString() + "/{" + column.toString() + "}";
        }
    }

    private void fillRequestParam(){
        for(Column column : conditionColumns){
            requestParams.add(new RequestParam(column.toString(), column.getColumnType()));
        }
    }



}
