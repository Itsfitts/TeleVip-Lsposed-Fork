package com.my.televip.language;


import android.content.Context;
import android.content.res.Configuration;

import com.my.televip.MainHook;

import java.util.Locale;

public class Language {
    public static String ToTheBeginning;
    public static String ToTheMessage;
    public static String  InputMessageId;
    public static String Done;
    public static String NewName;
    public static String Change;
    public static String Cancel;
    public static String ChangeTo;
    public static String NameDeleted;
    public static String GhostMode;
    public static String HideSeenUser;
    public static String HideSeenGroups;
    public static String HideStoryView;
    public static String HideTyping;
    public static String TelegramPremium;
    public static String UnlockAllRestricted;
    public static String AllowSavingvideos;
    public static String Ghost_Mode;
    public static String Save;
    public static String DeveloperChannel;
    public static String HideOnline;
    public static String PreventMedia;
    public static String HidePhone;
    public static String ShowDeletedMessages;
    public static String deleted;
    public static String Copied;
    public static String ToTheClipboard;
    public static String UserOffline;
    public static String DisableStories;
    public static String strTelevip="televip";

    public static void init(Context context)
    {
        if (getAppLanguage(context).equals("ar")) {
            ToTheBeginning="اذهب إلى أول رسالة";
            ToTheMessage="إلى الرسالة";
            InputMessageId="ادخل معرف الرسالة";
            Done="حسناً";
            NewName="الاسم الجديد";
            Change="تغير";
            Cancel="الغاء";
            ChangeTo="تم تغير الى";
            NameDeleted="تم حذف الاسم";
            GhostMode="وضع الشبح 👻";
            HideSeenUser="اخفاء علامة الاستلام من المحادثة الخاصة";
            HideSeenGroups="اخفاء علامة الاستلام من المجموعات والقنوات";
            HideStoryView="اخفاء مشاهدة قصة";
            HideTyping="اخفاء مؤشر الكتاب";
            TelegramPremium="فتح تيليجرام المميز";
            UnlockAllRestricted="فتح جميع الخصائص المشفره والمغلقه";
            AllowSavingvideos="سماح حفظ الفيديو في معرض";
            Ghost_Mode="مميزات وضع شبح";
            Save="حفظ";
            DeveloperChannel="قناة المطور";
            HideOnline="إخفاء حالة الاتصال بالإنترنت";
            PreventMedia ="تعطيل حذف الوسائط السرية";
            HidePhone="اخفاء رقم هاتف";
            ShowDeletedMessages="اضهار الرسائل المحذوفة";
            deleted="محذوفه";
            Copied = "تم نسخ '";
            ToTheClipboard = "' إلى الحافظة";
            UserOffline ="لست متصلاً بالإنترنت";
            DisableStories="اخفاء القصص";
        }else if (getAppLanguage(context).equals("zh")) {
            ToTheBeginning = "跳转到第一条消息";
            ToTheMessage = "跳转到消息";
            InputMessageId = "输入消息 ID";
            Done = "完成";
            NewName = "新名字";
            Change = "更改";
            Cancel = "取消";
            ChangeTo = "更改为";
            NameDeleted = "名称已删除";
            GhostMode = "幽灵模式 👻";
            HideSeenUser = "隐藏私人聊天的已读状态";
            HideSeenGroups = "隐藏群组和频道的已读状态";
            HideStoryView = "隐藏 '故事观看' 状态";
            HideTyping = "隐藏正在输入...";
            TelegramPremium = "启用 Telegram 本地会员";
            UnlockAllRestricted = "解锁频道的所有受限和加密功能";
            AllowSavingvideos = "允许将视频保存到图库";
            Ghost_Mode = "幽灵模式";
            Save = "保存";
            DeveloperChannel = "开发者频道";
            HideOnline = "隐藏 '在线' 状态";
            PreventMedia = "防止删除秘密媒体";
            HidePhone = "隐藏 '电话' 号码";
            ShowDeletedMessages = "显示已删除的消息";
            deleted = "已删除";
            Copied = "已复制 '";
            ToTheClipboard = "' 到剪贴板";
            UserOffline="您当前处于离线状态";
            DisableStories = "禁用 故事";
        }else {
            ToTheBeginning = "Go to First Message";
            ToTheMessage="To The Message";
            InputMessageId = "Input Message Id";
            Done = "Done";
            NewName = "New Name";
            Change = "Change";
            Cancel = "Cancel";
            ChangeTo="Change to";
            NameDeleted = "Name deleted";
            GhostMode="Ghost Mode 👻";
            HideSeenUser = "Hide 'Seen' status for private chats";
            HideSeenGroups = "Hide 'Seen' status for groups and channels";
            HideStoryView = "Hide 'Story View' status";
            HideTyping = "Hide Typing...";
            TelegramPremium = "Enable Telegram Premium";
            UnlockAllRestricted = "Unlock all restricted and encrypted features for channels";
            AllowSavingvideos = "Allow saving videos to the gallery";
            Ghost_Mode = "Ghost Mode";
            Save="Save";
            DeveloperChannel="Developer Channel";
            HideOnline="Hide 'Online' status";
            PreventMedia ="Prevent Deletion of Secret Media.";
            HidePhone ="Hide 'Phone' number";
            ShowDeletedMessages = "Show 'Deleted Messages'";
            deleted = "deleted";
            Copied = "Copied '";
            ToTheClipboard = "' to the clipboard";
            UserOffline = "You are currently offline";
            DisableStories ="Disable 'Stories'";
        }
    }
    public static String getAppLanguage(Context context) {
        Configuration config = context.getResources().getConfiguration();
        Locale locale;

        // تحقق من إصدار النظام للحصول على Locale بالطريقة الصحيحة
        locale = config.getLocales().get(0); // في الإصدارات الجديدة

        // إرجاع اسم اللغة
        return locale.getLanguage(); // مثال: "ar" للعربية
    }
}
