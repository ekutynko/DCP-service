package ru.lanit.ws.pgu.lod;

import ru.lanit.ws.pgu.lod.request.LodResponseStatus;

/**
 * Created by Sergey on 31.08.2014.
 */
public class LodBaseResponse {

    private Integer status;

    private String comment;

    public LodResponseStatus getStatus() {
        return LodResponseStatus.fromCode(status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
