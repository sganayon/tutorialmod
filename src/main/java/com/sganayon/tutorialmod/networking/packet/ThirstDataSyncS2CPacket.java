package com.sganayon.tutorialmod.networking.packet;

import com.sganayon.tutorialmod.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2CPacket {
    private final int thirst;

    public ThirstDataSyncS2CPacket(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf){
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier){
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // CLIENT STUFF HERE
            ClientThirstData.set(thirst);
        });
        return true;
    }
}
