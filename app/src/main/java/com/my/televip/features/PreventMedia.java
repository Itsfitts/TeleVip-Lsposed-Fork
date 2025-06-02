package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;

import de.robv.android.xposed.XposedHelpers;

public class PreventMedia {
private static Field messageOwnerField;
    public static void init() {
if (loadClass.MessageObjectClass == null) {
    loadClass.MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
}
        Class<?> ChatActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), lpparam.classLoader);

        if (loadClass.MessageObjectClass != null) {
            if (ChatActivityClass != null) {
                AutomationResolver.loadParameter("5");
                AutomationResolver.loadParameter("6");
                XposedHelpers.findAndHookMethod(ChatActivityClass, AutomationResolver.resolve("ChatActivity","sendSecretMessageRead", AutomationResolver.ResolverType.Method),AutomationResolver.merge(AutomationResolver.resolveObject("Parameter5"),  new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(null);
                    }
                }));
                XposedHelpers.findAndHookMethod(ChatActivityClass, AutomationResolver.resolve("ChatActivity","sendSecretMediaDelete", AutomationResolver.ResolverType.Method),AutomationResolver.merge(AutomationResolver.resolveObject("Parameter6"),  new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        param.setResult(null);
                    }
                }));
            }
            AutomationResolver.loadParameter("7");

            Class<?> SecretMediaViewerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.SecretMediaViewer"), lpparam.classLoader);

            XposedHelpers.findAndHookMethod(SecretMediaViewerClass,AutomationResolver.resolve("SecretMediaViewer","openMedia", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("Parameter7"), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) throws Throwable {
                    param.args[2] = null;
                    param.args[3] = null;
                    // الحصول على كائن ChatActivity
                    Object forwardingMessage = param.args[0];

                    if (forwardingMessage != null) {
                        // الوصول إلى الحقل messageOwner داخل forwardingMessage
                        Class<?> forwardingMessageClass = forwardingMessage.getClass();
                        if (messageOwnerField == null) {
                            messageOwnerField = forwardingMessageClass.getDeclaredField(AutomationResolver.resolve("MessageObject", "messageOwner", AutomationResolver.ResolverType.Field));
                            messageOwnerField.setAccessible(true);
                        }
                        Object messageOwner = messageOwnerField.get(forwardingMessage);

                        if (messageOwner != null) {
                            XposedHelpers.setObjectField(messageOwner, AutomationResolver.resolve("TLRPC$Message","ttl", AutomationResolver.ResolverType.Field), 0x7FFFFFFF);
                        }
                    }
                }
            }));
            XposedHelpers.findAndHookMethod(SecretMediaViewerClass,AutomationResolver.resolve("SecretMediaViewer","closePhoto", AutomationResolver.ResolverType.Method),AutomationResolver.merge(AutomationResolver.resolveObject("para6"), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    Object thisObject = param.thisObject;
                    XposedHelpers.setObjectField(thisObject, AutomationResolver.resolve("SecretMediaViewer","onClose", AutomationResolver.ResolverType.Field), null);
                }
            }));
        }

    }

}
