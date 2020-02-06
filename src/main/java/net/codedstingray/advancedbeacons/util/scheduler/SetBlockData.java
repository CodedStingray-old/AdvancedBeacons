package net.codedstingray.advancedbeacons.util.scheduler;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

//TODO: current implementation is inefficient; find more efficient implementation
public class SetBlockData extends BukkitRunnable {

    private Block block;
    private BlockData data;

    public SetBlockData(Block block, BlockData data) {
        this.block = block;
        this.data = data;
    }

    @Override
    public void run() {
        block.setBlockData(data);
    }
}
