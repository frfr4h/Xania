
package net.mcreator.xeniastuff.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.mcreator.xeniastuff.ElementsXeniastuff;

import com.google.common.collect.Multimap;

@ElementsXeniastuff.ModElement.Tag
public class ItemBoucliermedieval extends ElementsXeniastuff.ModElement {
	@GameRegistry.ObjectHolder("xeniastuff:boucliermedieval")
	public static final Item block = null;
	public ItemBoucliermedieval(ElementsXeniastuff instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemToolCustom() {
		}.setUnlocalizedName("boucliermedieval").setRegistryName("boucliermedieval").setCreativeTab(CreativeTabs.COMBAT));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("xeniastuff:boucliermedieval", "inventory"));
	}
	private static class ItemToolCustom extends Item {
		protected ItemToolCustom() {
			setMaxDamage(100);
			setMaxStackSize(1);
		}

		@Override
		public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
			Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
			if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 0f, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", -4, 0));
			}
			return multimap;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			IBlockState require;
			return 0;
		}

		@Override
		public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
			stack.damageItem(1, entityLiving);
			return true;
		}

		@Override
		public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
			stack.damageItem(2, attacker);
			return true;
		}

		@Override
		public boolean isFull3D() {
			return true;
		}

		@Override
		public int getItemEnchantability() {
			return 2;
		}
	}
}
