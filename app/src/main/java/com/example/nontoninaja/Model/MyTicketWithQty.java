package com.example.nontoninaja.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyTicketWithQty implements Parcelable {
    public MyTicket getTicket() {
        return ticket;
    }

    public void setTicket(MyTicket ticket) {
        this.ticket = ticket;
    }

    public int getCountReguler() {
        return countReguler;
    }

    public void setCountReguler(int countReguler) {
        this.countReguler = countReguler;
    }

    public int getCountVip() {
        return countVip;
    }

    public void setCountVip(int countVip) {
        this.countVip = countVip;
    }

    public int getCountVvip() {
        return countVvip;
    }

    public void setCountVvip(int countVvip) {
        this.countVvip = countVvip;
    }

    private MyTicket ticket;
    private int countReguler=0;
    private int countVip=0;
    private int countVvip=0;

    protected MyTicketWithQty(Parcel in) {
        this.ticket = in.readParcelable(MyTicket.class.getClassLoader());
        this.countReguler = in.readInt();
        this.countVip = in.readInt();
        this.countVvip = in.readInt();


    }

    public MyTicketWithQty(MyTicket ticket , int countReguler,int countVip, int countVvip){
        this.ticket = ticket;
        this.countReguler = countReguler;
        this.countVip = countVip;
        this.countVvip = countVvip;
    }


    public static final Parcelable.Creator<MyTicketWithQty> CREATOR = new Parcelable.Creator<MyTicketWithQty>()
    {

        @Override
        public MyTicketWithQty createFromParcel(Parcel parcel) {
            return new MyTicketWithQty(parcel);
        }

        @Override
        public MyTicketWithQty[] newArray(int i) {
            return new MyTicketWithQty[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(ticket,0);
        parcel.writeInt(countReguler);
        parcel.writeInt(countVip);
        parcel.writeInt(countVvip);
    }

}
