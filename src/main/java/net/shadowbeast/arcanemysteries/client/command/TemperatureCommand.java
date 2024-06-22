package net.shadowbeast.arcanemysteries.client.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.shadowbeast.arcanemysteries.temprature.TemperatureData;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;

public class TemperatureCommand
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("getTemperature")
                .executes(context -> {
                    CommandSourceStack source = context.getSource();
                    if (source.getEntity() instanceof ServerPlayer player) {
                        double temperature = EStats.getTemperatureStats(player).getDisplayTemperature();
                        String temperatureMessage = "Current temperature: " + temperature + " °C";
                        source.sendSuccess(()->Component.literal(temperatureMessage), false);
                    } else {
                        source.sendFailure(Component.literal("This command can only be run by a player."));
                    }
                    return 1;
                }));
    }
}
