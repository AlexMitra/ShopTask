package by.kalilaska.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SportEquipment implements Comparable<SportEquipment>{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportEquipment that = (SportEquipment) o;

        if (!id.equals(that.id)) return false;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SportEquipment{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }


    @Override
    public int compareTo(SportEquipment se) {

        if(this.category.getId() < se.getCategory().getId()){
            return -1;
        }
        else if(this.category.getId() > se.getCategory().getId()){
            return 1;
        }else {
            if(this.getId() < se.getId()){
                return -1;
            }else if(this.getId() > se.getId()){
                return 1;
            }else{
                return 0;
            }
        }
    }
}
