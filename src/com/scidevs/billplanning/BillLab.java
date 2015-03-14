 package com.scidevs.billplanning;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class BillLab {
	
    private static final String TAG = "BillLab";
    private static final String FILENAME = "bills.json";
    private BillPlanningJSONSerializer mSerializer;

    private static BillLab sBillsLab;
    private Context mAppContext;
    
    private double sSumPaid;
    private double sSumDept;
    
	private ArrayList<Bill> mBills;

	private BillLab(Context context) {
		mAppContext = context;
		mSerializer = new BillPlanningJSONSerializer(mAppContext, FILENAME);
		
        try {
            mBills = mSerializer.loadBills();
            Toast.makeText(mAppContext, "Bills Loaded Successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        	mBills = new ArrayList<Bill>();
        	Toast.makeText(mAppContext, "Error Loading Bills", Toast.LENGTH_SHORT).show();
        }
        
        sSumDept = 0;
        sSumPaid = 0;
        for (Bill b: mBills) {
        	if (b.isPaid()) addToPaid(b.getAmount()); else addToDept(b.getAmount());
        }
        
	}
	
    public void addBill(Bill b) {
        mBills.add(b);
    }
    
    public void deleteBill(Bill b) {
        mBills.remove(b);
    }
    
    public boolean saveBills() {
        try {
            mSerializer.saveBills(mBills);
            Log.d(TAG, "bills saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving bills: ", e);
            return false;
        }
    }

	public static BillLab get(Context c) {
		if (sBillsLab == null) {
			sBillsLab = new BillLab(c);
		}
		return sBillsLab;
	}

	public ArrayList<Bill> getBills() {
		return mBills;
	}

	public Bill getBill(UUID u) {
		for (Bill m : mBills) {
			if (m.getId().equals(u))
				return m;
		}
		return null;
	}
	
	public void copyBill(Bill b) {
		Bill bill = new Bill();
		bill.setAmount(b.getAmount());
		bill.setAmountPaid(b.getAmountPaid());
		bill.setCategory(b.getCategory());
		bill.setDateCreated(b.getDateCreated());
		bill.setDateDue(b.getDateDue());
		bill.setDateIssued(b.getDateIssued());
		bill.setInfo(b.getInfo());
		bill.setPaid(b.isPaid());
		bill.setPayCode(b.getPayCode());
		bill.setRepeated(b.isRepeated());
		bill.setTitle(b.getTitle());
		mBills.add(bill);
	}
	
	public void addToDept(double add) {
		sSumDept = sSumDept + add;
	}
	
	public void addToPaid(double add) {
		sSumPaid = sSumPaid + add;
	}
	
	public double getSumDept() {
		return sSumDept;
	}
	
	public double getSumPaid() {
		return sSumPaid;
	}
	
}
