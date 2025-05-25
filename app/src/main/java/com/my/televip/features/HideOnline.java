package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.StrVip;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class HideOnline {

    public static void init() {
        // العثور على الكلاسات المطلوبة للمعاملات
        Class<?> connectionsManagerClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.tgnet.ConnectionsManager"),
                lpparam.classLoader
        );
        if (connectionsManagerClass != null) {
            loadClass.loadObj9();
            XC_MethodHook hook = new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    try {
                        Class<?> tlAccountUpdateStatusClass;
                        if (lpparam.packageName.equals("com.tgconnect.android") || lpparam.packageName.equals("org.telegram.messenger.beta")) {
                            tlAccountUpdateStatusClass = XposedHelpers.findClassIfExists(
                                    "org.telegram.tgnet.TLRPC$TL_account_updateStatus",
                                    param.thisObject.getClass().getClassLoader()
                            );

                        } else {
                            tlAccountUpdateStatusClass = XposedHelpers.findClassIfExists(
                                    AutomationResolver.resolve("org.telegram.tgnet.tl.TL_account$updateStatus"), lpparam.classLoader
                            );
                        }
                        // التحقق من النوع وتعديله
                        Object object = param.args[0]; // أول معامل في الطريقة
                        if (tlAccountUpdateStatusClass.isInstance(object)) {
                            // تعديل الخاصية offline إلى true
                            XposedHelpers.setBooleanField(object, AutomationResolver.resolve("TL_account$updateStatus", "offline", AutomationResolver.ResolverType.Field), true);
                            // XposedBridge.log("Modified TL_account_updateStatus: offline set to true.");
                        }
                    } catch (Exception e) {
                        XposedBridge.log("Error while handling TL_account_updateStatus: " + e.getMessage());
                    }

                }
            };
            XposedHelpers.findMethodExact(connectionsManagerClass, "sendRequestInternal", AutomationResolver.merge(AutomationResolver.resolveObject("obj9"), hook));
            Class<?> profileActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity"), lpparam.classLoader);
            Class<?> baseFragmentClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.BaseFragment"), lpparam.classLoader);
            if (profileActivityClass != null) {
                XposedHelpers.findAndHookMethod(profileActivityClass,
                        "updateProfileData",
                        boolean.class,
                        new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) throws Throwable {
                                final Object profileActivityInstance = param.thisObject;
                                Method getUserConfigMethod = baseFragmentClass.getDeclaredMethod(AutomationResolver.resolve("BaseFragment","getUserConfig", AutomationResolver.ResolverType.Method));
                                getUserConfigMethod.setAccessible(true);
                                Object userConfig = getUserConfigMethod.invoke(profileActivityInstance);

                                Method getClientUserIdMethod;
                                if (userConfig != null) {
                                    getClientUserIdMethod = userConfig.getClass().getDeclaredMethod(AutomationResolver.resolve("UserConfig","getClientUserId", AutomationResolver.ResolverType.Method));

                                    getClientUserIdMethod.setAccessible(true);
                                    //noinspection DataFlowIssue
                                    long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);
                                    Field userIdField = profileActivityClass.getDeclaredField(AutomationResolver.resolve("ProfileActivity","userId", AutomationResolver.ResolverType.Field));
                                    userIdField.setAccessible(true);

                                    final long userId = userIdField.getLong(profileActivityInstance);
                                    if (userId != 0 && userId == clientUserId) {
                                        Object[] onlineTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, AutomationResolver.resolve("ProfileActivity","onlineTextView", AutomationResolver.ResolverType.Field));

                                        if (onlineTextViewArray != null && onlineTextViewArray.length > 1) {
                                            // الحصول على SimpleTextView[1]
                                            Object simpleTextView1 = onlineTextViewArray[1];

                                            if (simpleTextView1 != null) {
                                                // استدعاء setText باستخدام LSPosed
                                                XposedHelpers.callMethod(simpleTextView1, AutomationResolver.resolve("SimpleTextView","setText", AutomationResolver.ResolverType.Method), StrVip.onlinestatic);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                );
            }
        }
    }

}
