package yucl.learn.test;

/**
 * Created by YuChunlei on 2017/7/2.
 */
public class UWDto {
    String clientId;
    String clientName;
    ApplyDto applyDto;

    public UWDto() {
    }

    public UWDto(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public UWDto(String clientId, String clientName, ApplyDto applyDto) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.applyDto = applyDto;
    }

    public ApplyDto getApplyDto() {
        return applyDto;
    }

    public void setApplyDto(ApplyDto applyDto) {
        this.applyDto = applyDto;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
