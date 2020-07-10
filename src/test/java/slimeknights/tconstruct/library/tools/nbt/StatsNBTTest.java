package slimeknights.tconstruct.library.tools.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import slimeknights.tconstruct.library.MaterialRegistryExtension;
import slimeknights.tconstruct.test.BaseMcTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MaterialRegistryExtension.class)
class StatsNBTTest extends BaseMcTest {

  private final StatsNBT testStatsNBT = new StatsNBT(1, 2, 3, 4, 5, 6, 7, 8, true);

  @Test
  void serialize() {
    CompoundNBT nbt = testStatsNBT.serializeToNBT();
    
    assertThat(nbt.getInt(StatsNBT.TAG_DURABILITY)).isEqualTo(1);
    assertThat(nbt.getInt(StatsNBT.TAG_HARVESTLEVEL)).isEqualTo(2);
    assertThat(nbt.getFloat(StatsNBT.TAG_ATTACK)).isEqualTo(3);
    assertThat(nbt.getFloat(StatsNBT.TAG_MININGSPEED)).isEqualTo(4);
    assertThat(nbt.getFloat(StatsNBT.TAG_ATTACKSPEEDMULTIPLIER)).isEqualTo(5);
    assertThat(nbt.getInt(StatsNBT.TAG_FREE_MOD_SLOTS)).isEqualTo(6);
    assertThat(nbt.getInt(StatsNBT.TAG_FREE_ABILITY_SLOTS)).isEqualTo(7);
    assertThat(nbt.getInt(StatsNBT.TAG_FREE_ARMOR_SLOTS)).isEqualTo(8);
    assertThat(nbt.getBoolean(StatsNBT.TAG_BROKEN)).isTrue();
  }

  @Test
  void serializeEmpty_emptyList() {
    CompoundNBT nbt = StatsNBT.EMPTY.serializeToNBT();

    assertThat(nbt.size()).isEqualTo(9);
  }

  @Test
  void deserialize() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putInt(StatsNBT.TAG_DURABILITY, 8);
    nbt.putInt(StatsNBT.TAG_HARVESTLEVEL, 7);
    nbt.putFloat(StatsNBT.TAG_ATTACK, 6);
    nbt.putFloat(StatsNBT.TAG_MININGSPEED, 5);
    nbt.putFloat(StatsNBT.TAG_ATTACKSPEEDMULTIPLIER, 4);
    nbt.putInt(StatsNBT.TAG_FREE_MOD_SLOTS, 3);
    nbt.putInt(StatsNBT.TAG_FREE_ABILITY_SLOTS, 2);
    nbt.putInt(StatsNBT.TAG_FREE_ARMOR_SLOTS, 1);
    nbt.putBoolean(StatsNBT.TAG_BROKEN, false);

    StatsNBT statsNBT = StatsNBT.readFromNBT(nbt);

    assertThat(statsNBT.durability).isEqualTo(8);
    assertThat(statsNBT.harvestLevel).isEqualTo(7);
    assertThat(statsNBT.attack).isEqualTo(6);
    assertThat(statsNBT.miningSpeed).isEqualTo(5);
    assertThat(statsNBT.attackSpeedMultiplier).isEqualTo(4);
    assertThat(statsNBT.freeModSlots).isEqualTo(3);
    assertThat(statsNBT.freeAbilitySlots).isEqualTo(2);
    assertThat(statsNBT.freeArmorSlots).isEqualTo(1);
    assertThat(statsNBT.broken).isFalse();
  }

  @Test
  void deserializeNoData_empty() {
    CompoundNBT nbt = new CompoundNBT();

    StatsNBT statsNBT = StatsNBT.readFromNBT(nbt);

    assertThat(statsNBT).isEqualTo(StatsNBT.EMPTY);
  }

  @Test
  void wrongNbtType_empty() {
    INBT wrongNbt = new CompoundNBT();

    StatsNBT statsNBT = StatsNBT.readFromNBT(wrongNbt);

    assertThat(statsNBT).isEqualTo(StatsNBT.EMPTY);
  }
}
