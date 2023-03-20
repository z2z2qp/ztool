package com.github.z2z2qp.tool.bytes;

import junit.framework.TestCase;

/**
 * @since 2023/3/20 10:43
 */
public class SimpleBitmapTest extends TestCase {


    public void testSet() {
        var bitmap = new SimpleBitmap(new byte[1]);
        System.out.println(bitmap);
        bitmap.set(16);
        System.out.println(bitmap);
        bitmap.set(1);
        System.out.println(bitmap);
    }

    public void testReset() {
        var bitmap = new SimpleBitmap(new byte[]{(byte) 0xff});
        System.out.println(bitmap);
        bitmap.reset(3);
        System.out.println(bitmap);
    }
}