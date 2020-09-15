package poc.rest.request;

import poc.rest.config.configparcer.create.CreateEntry;

import java.util.List;

public class HttpRequestCreate {

    private String mapping = "";

    private List<CreateEntry> createEntries;

    public HttpRequestCreate(List<CreateEntry> createEntries) {
        this.createEntries = createEntries;
        fillMapping();
    }

    public String getMapping() {
        return mapping;
    }

    public List<CreateEntry> getCreateEntries() {
        return createEntries;
    }

    //     /tableName1/tableName2 - создаются строки в 2х таблицах
    private void fillMapping() {
        for (CreateEntry entry : createEntries) {
            mapping += "/" + entry.getTableName();
        }
    }

}
