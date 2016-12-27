package com.example.a.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a on 2016/12/23.
 */

public class ProductTitleMessageModel extends BaseModel{

    /**
     * o : [{"rid":"5850a2072d0b1","name":"小学长","head":"/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","details":[{"brand":"Jack Johns","category":"上衣","id":"1"},{"brand":"zara","category":"下装","id":"2"}]},{"rid":"5850a5ecc3efe","name":"小学长","head":"/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","details":[{"brand":"Jack Johns","category":"上衣","id":"1"},{"brand":"zara","category":"下装","id":"2"}]}]
     * e : true
     */

    private boolean e;
    private List<OBean> o;

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public List<OBean> getO() {
        return o;
    }

    public void setO(List<OBean> o) {
        this.o = o;
    }

    public static class OBean  implements Parcelable {
        /**
         * rid : 5850a2072d0b1
         * name : 小学长
         * head : /Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg
         * details : [{"brand":"Jack Johns","category":"上衣","id":"1"},{"brand":"zara","category":"下装","id":"2"}]
         */

        private String rid;
        private String name;
        private String head;
        private List<DetailsBean> details;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        @Override
        public int describeContents() {
            return 0;
        }
        public OBean(Parcel in)
        {
            rid = in.readString();
            name = in.readString();
            head = in.readString();
            details = in.readArrayList(DetailsBean.class.getClassLoader());
        }
        @Override
        public void writeToParcel(Parcel parcel, int i) {

            parcel.writeString(rid);
            parcel.writeString(name);
            parcel.writeString(head);
            parcel.writeTypedList(details);
        }


        public static final Parcelable.Creator<OBean> CREATOR = new Creator<OBean>() {

            @Override
            public OBean[] newArray(int size) {
                // TODO Auto-generated method stub
                return new OBean[size];
            }

            @Override
            public OBean createFromParcel(Parcel source) {
                // TODO Auto-generated method stub
                return new OBean(source);
            }
        };

        public static class DetailsBean implements Parcelable {
            /**
             * brand : Jack Johns
             * category : 上衣
             * id : 1
             */

            private String brand;
            private String category;
            private String id;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public int describeContents() {
                return 0;
            }
            public DetailsBean(Parcel in)
            {
                brand = in.readString();
                category = in.readString();
                id = in.readString();
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {

                parcel.writeString(brand);
                parcel.writeString(category);
                parcel.writeString(id);
                parcel.writeString(id);
            }
            public static final Parcelable.Creator<DetailsBean> CREATOR = new Creator<DetailsBean>() {

                @Override
                public DetailsBean[] newArray(int size) {
                    // TODO Auto-generated method stub
                    return new DetailsBean[size];
                }

                @Override
                public DetailsBean createFromParcel(Parcel source) {
                    // TODO Auto-generated method stub
                    return new DetailsBean(source);
                }
            };
        }
    }
}
