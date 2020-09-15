package poc.rest.request;

import poc.rest.config.configparcer.update.UpdateEntry;

import java.util.List;

public class HttpRequestUpdate{

    private String mapping = "";

    private List<UpdateEntry> updateEntries;

    public HttpRequestUpdate(List<UpdateEntry> updateEntries) {
        this.updateEntries = updateEntries;
        fillMapping();
    }

    public String getMapping() {
        return mapping;
    }

    public List<UpdateEntry> getUpdateEntries() {
        return updateEntries;
    }

    //     /tableName1/tableName2 - апдейтятся 2 таблицы в одной транзакции
    private void fillMapping() {
        for (UpdateEntry entry : updateEntries) {
            mapping += "/" + entry.getTableName();
        }
    }
}
