package poc.rest.request;

import poc.rest.config.configparcer.delete.DeleteEntry;

import java.util.List;

public class HttpRequestDelete {

    private String mapping = "";

    private List<DeleteEntry> deleteEntries;

    public HttpRequestDelete(List<DeleteEntry> deleteEntries) {
        this.deleteEntries = deleteEntries;
        fillMapping();
    }

    public String getMapping() {
        return mapping;
    }

    public List<DeleteEntry> getDeleteEntries() {
        return deleteEntries;
    }

    //     /tableName1/tableName2 - удаляются данные из 2х таблиц
    private void fillMapping() {
        for (DeleteEntry entry : deleteEntries) {
            mapping += "/" + entry.getTableName();
        }
    }

}
