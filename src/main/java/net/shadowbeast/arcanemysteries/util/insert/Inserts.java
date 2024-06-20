package net.shadowbeast.arcanemysteries.util.insert;

import net.shadowbeast.arcanemysteries.util.insert.handler.LevelHandler;
import net.shadowbeast.arcanemysteries.util.insert.handler.LivingInsertHandler;
import net.shadowbeast.arcanemysteries.util.insert.handler.PlayerInsertHandler;

public class Inserts
{
    public static final LivingInsertHandler LIVING_TICK = new LivingInsertHandler();
    public static final LevelHandler LEVEL_LOAD = new LevelHandler();
    public static final PlayerInsertHandler LOGGED_OUT = new PlayerInsertHandler();}
