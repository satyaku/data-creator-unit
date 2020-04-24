package com.entry.data.data.creator.unit.datalayer.interfaces;

import com.entry.data.data.creator.unit.model.EntryResponse;

import java.sql.Connection;
import java.util.Queue;

public interface IEntityInsertionRepository {

    public EntryResponse performInsert(Queue<String> query, Connection conn);

}
