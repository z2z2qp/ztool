package com.github.z2z2qp.tool.bytes;

/**
 * <p>
 * byte数组读取工具，
 * </p>
 * <li>{@link #readByte()}</li>
 * <li>{@link #readUnsignedByte()}</li>
 * <li>{@link #readShort()}</li>
 * <li>{@link #readUnsignedShort()}</li>
 * <li>{@link #readInt()}</li>
 * <li>{@link #readUnsignedInt()}</li>
 * <li>{@link #readLong()}</li>
 * <li>{@link #readFloat()}</li>
 * <li>{@link #readDouble()} }</li>
 * <li>{@link #readUnsignedInt()}</li>
 * <li>{@link #skip(int)}</li>
 * 从第一位开始往后读取不可回头
 * <li>{@link #getByte(int)}</li>
 * <li>{@link #getShort(int)}</li>
 * <li>{@link #getInt(int)}</li>
 * <li>{@link #getLong(int)}</li>
 * <li>{@link #getFloat(int)}</li>
 * <li>{@link #getDouble(int)}</li>
 * 读取任意位置开始的数据
 */
public class BytesReader {
    /**
     * 解析的byte数组
     */
    private final byte[] cache;
    /**
     * 游标
     */
    private int index;

    /**
     * 构造方法
     *
     * @param bytes 需要读取的byte数组
     */
    public BytesReader(byte[] bytes) {
        this.cache = bytes;
        index = 0;
    }

    /**
     * 跳过n个字节
     *
     * @param skip 需要跳过的数量
     */
    public void skip(int skip) {
        index += skip;
        check();

    }

    /**
     * 顺序读取下一个byte
     *
     * @return 读到的byte
     */
    public byte readByte() {
        check();
        return cache[index++];
    }

    /**
     * 顺序读取下一个byte，无符号
     *
     * @return 读到的无符号byte
     */
    public short readUnsignedByte() {
        var value = readByte();
        return (short) (value & 0xFF);
    }

    /**
     * 顺序读取下一个short
     *
     * @return 读到的short
     */
    public short readShort() {
        var h = readByte();
        var l = readByte();
        return (short) ((h & 0xFF) << 8 | (l & 0xFF));
    }


    /**
     * 顺序读取下一个short，无符号
     *
     * @return 读到的无符号short
     */
    public int readUnsignedShort() {
        var value = readShort();
        return value & 0xFFFF;
    }


    /**
     * 顺序读取下一个int
     *
     * @return 读到的int
     */
    public int readInt() {
        var h = readShort();
        var l = readShort();
        return ((h & 0xFFFF) << 16 | (l & 0xFFFF));
    }


    /**
     * 顺序读取下一个int，无符合
     *
     * @return 读到的无符号int
     */
    public long readUnsignedInt() {
        var value = readInt();
        return value & 0xFFFFFFFFL;
    }


    /**
     * 顺序读取下一个long
     *
     * @return 读到的long
     */
    public long readLong() {
        var h = readInt();
        var l = readInt();
        return ((h & 0xFFFFFFFFL) << 32 | (l & 0xFFFFFFFFL));
    }

    /**
     * 从 index 开始获取一个byte
     *
     * @param index 起始位置
     * @return 获得的byte
     */
    public byte getByte(int index) {
        check();
        return cache[index];
    }

    /**
     * 从 index 开始获取一个short
     *
     * @param index 起始位置
     * @return 获得的short
     */
    public short getShort(int index) {
        var h = getByte(index);
        var l = getByte(index + 1);
        return (short) ((h & 0xFF) << 8 | (l & 0xFF));
    }

    /**
     * 从 index 开始获取一个int
     *
     * @param index 起始位置
     * @return 获得的int
     */
    public int getInt(int index) {
        var h = getShort(index);
        var l = getShort(index + 2);
        return ((h & 0xFFFF) << 16 | (l & 0xFFFF));
    }

    /**
     * 从 index 开始获取一个long
     *
     * @param index 起始位置
     * @return 获得的long
     */
    public long getLong(int index) {
        var h = getInt(index);
        var l = getInt(index + 4);
        return ((h & 0xFFFFFFFFL) << 32 | (l & 0xFFFFFFFFL));
    }

    /**
     * 顺序读取下一个float
     *
     * @return 获得的float
     */
    public float readFloat() {
        var value = readInt();
        return Float.intBitsToFloat(value);
    }

    /**
     * 顺序读取下一个double
     *
     * @return 获得的double
     */
    public double readDouble() {
        var value = readLong();
        return Double.longBitsToDouble(value);
    }

    /**
     * 从 index 开始获取一个float
     *
     * @param index 起始位置
     * @return 获得的float
     */
    public float getFloat(int index) {
        var value = getInt(index);
        return Float.intBitsToFloat(value);
    }

    /**
     * 从 index 开始获取一个double
     *
     * @param index 起始位置
     * @return 获得的double
     */
    public double getDouble(int index) {
        var value = getLong(index);
        return Double.longBitsToDouble(value);
    }

    private void check() {
        boolean flag = index >= 0 && index < cache.length;
        if (!flag) {
            throw new IndexOutOfBoundsException(String.format("下标%s,不在[0,%s)范围内了", index, cache.length));
        }
    }
}
