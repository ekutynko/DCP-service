package my.app.stub;

/**
 * Created by user06 on 01.09.2014.
 */
public class LodResponse {

    private Integer status;

    private String comment;

    private Long licenseRequestId;

    public Integer getStatus() {
        return status;
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

    public Long getLicenseRequestId() {
        return licenseRequestId;
    }

    public void setLicenseRequestId(Long licenseRequestId) {
        this.licenseRequestId = licenseRequestId;
    }
}
