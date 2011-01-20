package de.uniluebeck.itm.nettyrxtx.rup;


import com.google.common.base.Preconditions;
import de.uniluebeck.itm.nettyrxtx.isense.ISensePacket;
import de.uniluebeck.itm.nettyrxtx.isense.ISensePacketType;

public class RUPPacketHelper {

	/**
	 * Checks if {@code type} is one of {@link RUPPacketType#MESSAGE}, {@link RUPPacketType#SINK_REQUEST}
	 * or {@link RUPPacketType#SINK_RESPONSE}.
	 *
	 * @param type the byte indicating the packet type
	 *
	 * @return {@code true} if type is a remote UART packet type, {@code false} otherwise
	 */
	public static boolean isRemoteUARTPacket(byte type) {
		Preconditions.checkNotNull(type);
		return type == RUPPacketType.MESSAGE.getValue() ||
				type == RUPPacketType.SINK_REQUEST.getValue() ||
				type == RUPPacketType.SINK_RESPONSE.getValue() ||
				type == RUPPacketType.SET_SINK.getValue();
	}

	/**
	 * Checks if the first byte of {@code bytes} is one of {@link RUPPacketType#MESSAGE}, {@link
	 * RUPPacketType#SINK_REQUEST} or {@link RUPPacketType#SINK_RESPONSE}.
	 *
	 * @param bytes the packets byte-array representation
	 *
	 * @return {@code true} if the packets type is a remote UART packet type, {@code false} otherwise
	 */
	public static boolean isRemoteUARTPacket(byte[] bytes) {
		Preconditions.checkNotNull(bytes);
		Preconditions.checkArgument(bytes.length > 0);
		return isRemoteUARTPacket(bytes[0]);
	}

	/**
	 * Checks if the {@code iSensePacket} contains a Remote UART packet.
	 * <p/>
	 * {@code iSensePacket} contains a Remote UART packet if it is of type {@link ISensePacketType#PLOT} and if the first
	 * byte of its payload is one of {@link RUPPacketType#MESSAGE}, {@link RUPPacketType#SINK_REQUEST} or
	 * {@link RUPPacketType#SINK_RESPONSE}.
	 *
	 * @param packet the packet
	 *
	 * @return {@code true} if the packets type is a remote UART packet type, {@code false} otherwise
	 */
	public static boolean isRemoteUARTPacket(final ISensePacket packet) {
		return ISensePacketType.PLOT.equals(ISensePacketType.fromValue(packet.getType())) &&
				isRemoteUARTPacket(packet.getPayload().getByte(0));
	}

}
