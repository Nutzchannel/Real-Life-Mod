package de.ItsAMysterious.mods.reallifemod.api.Handlers;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RLMPacketHandler implements IMessageHandler<IMessage, IMessage>, IMessage{

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	@Override
	public IMessage onMessage(IMessage message, MessageContext ctx) {
		return null;
	}

}
