package com.github.z2z2qp.tool.bytes;

/**
 * 简单的bitmap
 *
 * @since 2023/3/20 10:16
 */
public class SimpleBitmap {

    /**
     *存储bit数据
     */
    private byte[] value;

    /**
     * 通过byte数组 大小构建
     * @param size byte数组的size
     */
    public SimpleBitmap(int size) {
        value = new byte[size];
    }

    /**
     * 通过一个byte数组构建
     * @param value byte数组
     */
    public SimpleBitmap(byte[] value) {
        this.value = value;
    }

    /**
     * 获取全部值
     * @return 全部值
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * 一个byte一组二进制输出
     * @return 转换后的值
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : value) {
            for (int i = 7; i >= 0; i--) {
                sb.append((b >> i) & 1);
            }
            sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * <p>1  0 101010 01010101</p>
     * <p>15 14 ……           0</p>
     * 获取第index位的值 高位在前
     *
     * @param index 序号
     * @return 值
     */
    public int get(int index) {
        if (check(index)) {
            int temp = value.length - (index / 8 + 1);//检索在第几个byte数组内
            int temp2 = index % 8;//检索在byte中的位置
            return (value[temp] >> temp2) & 1;
        } else {
            throw new IndexOutOfBoundsException(index + " is out of max:" + (value.length * 8 - 1));
        }

    }

    /**
     * 设置第index位的值为1 高位在前
     * 数组长度不够自动扩容
     *
     * @param index 序号
     */
    public void set(int index) {
        if (check(index)) {//未超过最大长度直接设置
            int temp = value.length - (index / 8 + 1);
            int temp2 = index % 8;
            value[temp] |= (byte) (1 << temp2);
        } else {//超过最大长度数组扩容后设值
            byte[] bytes = new byte[index / 8 + 1];
            System.arraycopy(value, 0, bytes, bytes.length - value.length, value.length);
            value = bytes;
            set(index);
        }
    }

    /**
     * 重置第index位的值为0 高位在前
     *
     * @param index 序号
     */
    public void reset(int index) {
        if (check(index)) {
            int temp = value.length - (index / 8 + 1);
            int temp2 = index % 8;
            value[temp] &= (byte) ~(1 << temp2);
        } else {
            throw new IndexOutOfBoundsException(index + " is out of max:" + (value.length * 8 - 1));
        }
    }

    /**
     * 判断是否超过最大长度
     * @param index 位置
     * @return true 未超过，false超过
     */
    private boolean check(int index) {
        return index < value.length * 8;
    }


}
