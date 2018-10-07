package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;
import android.widget.RadioGroup;

import java.util.Date;
import java.util.UUID;
import java.lang.String;

public class Invoice {
    private UUID invoiceId;

    private String invoiceTitle;
    private Date invoiceDate;
    private String invoiceShopName;
    private String invoiceComment;

    public String getInvoiceChooseType() {
        return invoiceChooseType;
    }

    public void setInvoiceChooseType(String invoiceChooseType) {
        this.invoiceChooseType = invoiceChooseType;
    }

    private String invoiceChooseType;


    public RadioGroup getInvoiceRadio() {
        return invoiceRadio;
    }

    public void setInvoiceRadio(RadioGroup invoiceRadio) {
        this.invoiceRadio = invoiceRadio;
    }

    private RadioGroup invoiceRadio;

    private boolean invoiceSolved;
    public Invoice() {
        this(UUID.randomUUID());
    }
    public Invoice(UUID id) {
        invoiceId = id;
        invoiceDate = new Date(); }
    public UUID getId() {
        return invoiceId;
    }
    public String getTitle() {
        return invoiceTitle;    }
    public void setTitle(String title) {
        invoiceTitle = title;    }
    public Date getDate() {
        return invoiceDate;
    }
    public void setDate(Date date) {
        invoiceDate = date;
    }
    public boolean isSolved()
    {
        return invoiceSolved;
    }
    public void setSolved(boolean solved) {
        invoiceSolved = solved;
    }


    public String getShopName() {
        return invoiceShopName;
    }

    public void setShopName(String shopName) {
        invoiceShopName = shopName;
    }

    public String getComment() {
        return invoiceComment;
    }

    public void setComment(String comment) {
        invoiceComment = comment;
    }

    public String getImageFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }


}
