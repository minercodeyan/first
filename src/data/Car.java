package data;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Car {
 StringProperty id;
 StringProperty mark;
 StringProperty user;
 StringProperty prise;
 StringProperty km;

    public Car(String id, String mark, String user,String prise, String km) {
     this.id=new SimpleStringProperty(String.valueOf(id));
     this.mark= new SimpleStringProperty(mark);
     this.user = new SimpleStringProperty(user);
     this.prise = new SimpleStringProperty(prise);
     this.km= new SimpleStringProperty(km);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getMark() {
        return mark.get();
    }

    public StringProperty markProperty() {
        return mark;
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public String getPrise() {
        return prise.get();
    }

    public StringProperty priseProperty() {
        return prise;
    }

    public String getKm() {
        return km.get();
    }

    public StringProperty kmProperty() {
        return km;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setPrise(String prise) {
        this.prise.set(prise);
    }

    public void setKm(String km) {
        this.km.set(km);
    }
}


