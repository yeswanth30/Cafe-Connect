package com.coffeeshop.models;

public class home {
    private Integer id;

    private String itemname;
    private String price;
    private String photo;

      public  home( String itemname,String price, String imagepath){

         this.itemname = itemname;
         this.price = price;
         this.photo = photo;
        }
    public  home(int id, String itemname, String price, String photo){
        this.id = id;
        this.itemname = itemname;
        this.price = price;
        this.photo = photo;
    }

    public home(Integer id) {
    }

    public home(String itemname) {
    }

    public home(String itemname, String price) {
        this.itemname = itemname;
        this.price = price;
    }


    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
