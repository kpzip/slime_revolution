package io.github.kpzip.slimerevolution.common.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import io.github.kpzip.slimerevolution.common.container.IndustrialBrewerContainer;
import io.github.kpzip.slimerevolution.core.ModVars;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class IndustrialBrewerScreen extends ContainerScreen<IndustrialBrewerContainer>{
	
	public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(ModVars.MOD_ID, "textures/gui/industrial_brewer.png");

	public IndustrialBrewerScreen(IndustrialBrewerContainer container, PlayerInventory playerInv, ITextComponent title) {
		super(container, playerInv, title);
		
		this.imageWidth = 176;
		this.imageHeight = 176;
	}
	
	@Override
	public void render(MatrixStack stack, int x, int y, float partialTicks) {
		renderBackground(stack);
		super.render(stack, x, y, partialTicks);
		this.renderTooltip(stack, x, y);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int x, int y) {
		if (minecraft == null) {
			return;
		}
		
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		minecraft.getTextureManager().bind(GUI_TEXTURE);
		
		int posX = (this.width - this.imageWidth) / 2;
		int posY = (this.height - this.imageHeight) / 2;
		
		blit(stack, posX, posY, 0, 0, this.imageWidth, this.imageHeight);
	}

}
