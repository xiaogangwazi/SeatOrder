package bean;

import common.Message;
public class Login {

    private String code;
    private String login_name;
    private String org_id;
    private String password;
    private String str;
    private String _ApplicationId;
    private String _ClientVersion;
    private String _InstallationId;
    private String _JavaScriptKey;
    public Login(String login_name){
        this.login_name=login_name;
        init();
    }
    private void init(){
        this.code= Message.Login.code;
        this.password=Message.Login.password;
        this.org_id=Message.Login.org_id;
        this._ApplicationId=Message.Login._ApplicationId;
        this._ClientVersion=Message.Login._ClientVersion;
        this._InstallationId=Message.Login._InstallationId;
        this._JavaScriptKey=Message.Login._JavaScriptKey;
        this.str=Message.Login.str;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String get_ApplicationId() {
        return _ApplicationId;
    }

    public void set_ApplicationId(String _ApplicationId) {
        this._ApplicationId = _ApplicationId;
    }

    public String get_ClientVersion() {
        return _ClientVersion;
    }

    public void set_ClientVersion(String _ClientVersion) {
        this._ClientVersion = _ClientVersion;
    }

    public String get_InstallationId() {
        return _InstallationId;
    }

    public void set_InstallationId(String _InstallationId) {
        this._InstallationId = _InstallationId;
    }

    public String get_JavaScriptKey() {
        return _JavaScriptKey;
    }

    public void set_JavaScriptKey(String _JavaScriptKey) {
        this._JavaScriptKey = _JavaScriptKey;
    }
}
