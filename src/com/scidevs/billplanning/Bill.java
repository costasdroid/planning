package com.scidevs.billplanning;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Bill {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_PAID = "paid";
    private static final String JSON_DATE_CREATED = "datecreated";
    private static final String JSON_DATE_ISSUED = "dateissued";
    private static final String JSON_DATE_DUE = "datedue";
    private static final String JSON_DATE_PAYMENT = "datepayment";
    private static final String JSON_INFO = "info";
    private static final String JSON_AMOUNT = "amount";
    private static final String JSON_AMOUNT_PAID = "amountpaid";
    private static final String JSON_PAYCODE = "paycode";
    private static final String JSON_REPEATED = "repeated";
    private static final String JSON_CATEGORY = "category";
	
	private UUID mId;
	private String mTitle;
	private Date mDateCreated;
	private Date mDateDue;
	private Date mDateIssued;
	private Date mDatePayment;
	private String mInfo;
	private boolean mPaid;
	private boolean mRepeated;
	private String mPayCode;
	private Double mAmount;
	private Double mAmountPaid;
	private UUID mCategory;
	
	public Bill() {
		// Generate unique identifier
		mId = UUID.randomUUID();
		mDateCreated = new Date();
		mDateDue = new Date();
		mDateIssued = new Date();
		mDatePayment = new Date();
		mAmount = 0.;
		mAmountPaid = 0.;
		mPaid = false;
		mRepeated = false;
	}
	
    public Bill(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mDateCreated = new Date(json.getLong(JSON_DATE_CREATED));
    	mDateDue = new Date(json.getLong(JSON_DATE_DUE));
    	mDateIssued = new Date(json.getLong(JSON_DATE_ISSUED));
    	mDatePayment = new Date(json.getLong(JSON_DATE_PAYMENT));
    	mInfo = json.getString(JSON_INFO);
        mPaid = json.getBoolean(JSON_PAID);
    	mRepeated = json.getBoolean(JSON_REPEATED);
    	mPayCode = json.getString(JSON_PAYCODE);
    	mAmount = json.getDouble(JSON_AMOUNT);
    	mAmountPaid = json.getDouble(JSON_AMOUNT_PAID);
    	if (!json.isNull(JSON_CATEGORY)) mCategory = UUID.fromString(json.getString(JSON_CATEGORY));
    }

	@Override
	public String toString() {
		return mTitle;
	}

	public Date getDateCreated() {
		return mDateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		mDateCreated = dateCreated;
	}

	public Date getDateDue() {
		return mDateDue;
	}

	public void setDateDue(Date date) {
		mDateDue = date;
	}

	public Date getDateIssued() {
		return mDateIssued;
	}

	public void setDateIssued(Date date) {
		mDateIssued = date;
	}

	public Date getDatePayment() {
		return mDatePayment;
	}

	public void setDatePayment(Date date) {
		mDatePayment = date;
	}
	
	public String getInfo() {
		return mInfo;
	}

	public void setInfo(String info) {
		mInfo = info;
	}

	public boolean isPaid() {
		return mPaid;
	}

	public void setPaid(boolean paid) {
		mPaid = paid;
	}

	public UUID getId() {
		return mId;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getPayCode() {
		return mPayCode;
	}

	public void setPayCode(String payCode) {
		mPayCode = payCode;
	}

	public boolean isRepeated() {
		return mRepeated;
	}

	public void setRepeated(boolean repeated) {
		mRepeated = repeated;
	}

	public Double getAmount() {
		return mAmount;
	}

	public void setAmountPaid(Double amount) {
		mAmountPaid = amount;
	}
	
	public Double getAmountPaid() {
		return mAmountPaid;
	}

	public void setAmount(Double amount) {
		mAmount = amount;
	}	

	public UUID getCategory() {
		return mCategory;
	}

	public void setCategory(UUID category) {
		mCategory = category;
	}

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        if (mTitle == null) json.put(JSON_TITLE, JSONObject.NULL); else json.put(JSON_TITLE, mTitle);
        json.put(JSON_PAID, mPaid);
        json.put(JSON_DATE_CREATED, mDateCreated.getTime());
        json.put(JSON_AMOUNT, mAmount);
        json.put(JSON_AMOUNT_PAID, mAmountPaid);
        json.put(JSON_DATE_DUE, mDateDue.getTime());
        json.put(JSON_DATE_ISSUED, mDateIssued.getTime());
        json.put(JSON_DATE_PAYMENT, mDatePayment.getTime());
        json.put(JSON_CATEGORY, mCategory);
        if (mInfo == null) json.put(JSON_INFO, JSONObject.NULL); else json.put(JSON_INFO, mInfo);
        if (mPayCode == null) json.put(JSON_PAYCODE, JSONObject.NULL); else json.put(JSON_PAYCODE, mPayCode);
        json.put(JSON_REPEATED, mRepeated);
        return json;
    }
 
}
