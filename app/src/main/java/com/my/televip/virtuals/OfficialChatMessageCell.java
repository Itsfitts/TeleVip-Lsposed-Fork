package com.my.televip.virtuals;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.Utils;
import com.my.televip.utils.FieldUtils;

public class OfficialChatMessageCell extends ChatMessageCellDefault {
    public OfficialChatMessageCell(Object instance) {
        super(instance);
    }

    public CharSequence getCurrentTimeString()
    {
        return (CharSequence) FieldUtils.getFieldClassOfClass(this.instance, "currentTimeString");
    }

    public void setCurrentTimeString(CharSequence currentTimeString)
    {
        try
        {
            XposedHelpers.setObjectField(this.instance, "currentTimeString", currentTimeString);
            /*Field currentTimeStringField = FieldUtils.getFieldOfClass(this.instance, "currentTimeString");
            if (currentTimeStringField != null)
                currentTimeStringField.set(this.instance, currentTimeString);
            else
                throw new NullPointerException("Not found currentTimeString in " + this.instance.getClass().getName());*/
        }
        catch (Throwable e)
        {
            Utils.log(e);
        }
    }
}
