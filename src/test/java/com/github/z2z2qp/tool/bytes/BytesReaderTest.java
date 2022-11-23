package com.github.z2z2qp.tool.bytes;

import junit.framework.TestCase;

public class BytesReaderTest extends TestCase {

    private final byte[] bs = {0x01, 0x02, (byte) 0xF3, (byte) 0xF4, (byte) 0xF1, (byte) 0xF2, 0x03, 0x04};

    public void testSkip() {
        var reader = new BytesReader(bs);
        try {
            reader.skip(8);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void testReadByte() {
        var reader = new BytesReader(bs);
        var b1 = reader.readByte();
        var b2 = reader.readByte();
        assertEquals((byte) 0x01, b1);
        assertEquals((byte) 0x02, b2);
    }

    public void testReadUnsignedByte() {
        var reader = new BytesReader(bs);
        reader.skip(2);
        var b1 = reader.readUnsignedByte();
        var b2 = reader.readUnsignedByte();
        assertTrue(b1 > 0);
        assertTrue(b2 > 0);
    }

    public void testReadShort() {
        var reader = new BytesReader(bs);
        var b1 = reader.readShort();
        var b2 = reader.readShort();
        assertEquals((short)0x0102, b1);
        assertEquals((short)0xF3F4, b2);
    }

    public void testReadUnsignedShort() {
        var reader = new BytesReader(bs);
        var b1 = reader.readUnsignedShort();
        var b2 = reader.readUnsignedShort();
        assertTrue(b1 > 0);
        assertTrue(b2 > 0);
    }

    public void testReadInt() {
        var reader = new BytesReader(bs);
        var b1 = reader.readInt();
        var b2 = reader.readInt();
        assertEquals(0x0102F3F4, b1);
        assertEquals(0xF1F20304, b2);
    }

    public void testReadUnsignedInt() {
        var reader = new BytesReader(bs);
        var b1 = reader.readUnsignedInt();
        var b2 = reader.readUnsignedInt();
        assertTrue(b1 > 0);
        assertTrue(b2 > 0);
    }

    public void testReadLong() {
        var reader = new BytesReader(bs);
        var b1 = reader.readLong();
        assertEquals(0x0102F3F4F1F20304L, b1);
    }

    public void testGetByte() {
        var reader = new BytesReader(bs);
        var b1 = reader.getByte(3);
        var b2 = reader.getByte(4);
        assertEquals((byte) 0xF4, b1);
        assertEquals((byte) 0xF1, b2);
    }

    public void testGetShort() {
        var reader = new BytesReader(bs);
        var b1 = reader.getShort(3);
        var b2 = reader.getShort(4);
        assertEquals((short) 0xF4F1, b1);
        assertEquals((short) 0xF1F2, b2);
    }

    public void testGetInt() {
        var reader = new BytesReader(bs);
        var b1 = reader.getInt(3);
        var b2 = reader.getInt(4);
        assertEquals(0xF4F1F203, b1);
        assertEquals(0xF1F20304, b2);
    }

    public void testGetLong() {
        var reader = new BytesReader(bs);
        var b1 = reader.getLong(0);
        assertEquals(0x0102F3F4F1F20304L, b1);

    }

    public void testReadFloat() {
        var reader = new BytesReader(bs);
        reader.skip(2);
        var b1 = reader.readFloat();
        assertEquals(Float.intBitsToFloat(0xF3F4F1F2), b1);

    }

    public void testReadDouble() {
        var reader = new BytesReader(bs);
        var b1 = reader.readDouble();
        assertEquals(Double.longBitsToDouble(0x0102F3F4F1F20304L), b1);
    }

    public void testGetFloat() {
        var reader = new BytesReader(bs);
        var b1 = reader.getFloat(3);
        assertEquals(Float.intBitsToFloat(0xF4F1F203), b1);
    }

    public void testGetDouble() {
        var reader = new BytesReader(bs);
        var b1 = reader.getDouble(0);
        assertEquals(Double.longBitsToDouble(0x0102F3F4F1F20304L), b1);
    }
}