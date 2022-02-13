package com.maker.dao;

public class Ads {
private int aid;
private String title;
private String url;
private String photo;
private String note;
public int getAid() {
	return aid;
}
public void setAid(int aid) {
	this.aid = aid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
@Override
public String toString() {
	return "Ads [aid=" + aid + ", title=" + title + ", url=" + url + ", photo=" + photo + ", note=" + note + "]";
}
}
