package co.uk.silvania.advancedarmoury.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TinyLootCrateModel extends ModelBase {

	ModelRenderer base;
	ModelRenderer top;
	ModelRenderer rightSide;
	ModelRenderer leftSide;
	ModelRenderer back;
	
	public TinyLootCrateModel() {
		textureWidth = 64;
		textureHeight = 32;
	
		base = new ModelRenderer(this, 0, 23);
		base.addBox(0F, 0F, 0F, 8, 1, 8);
		base.setRotationPoint(-4F, 23F, -4F);
		base.setTextureSize(64, 32);
		setRotation(base, 0F, 0F, 0F);
		
		top = new ModelRenderer(this, 32, 23);
		top.addBox(0F, 0F, 0F, 8, 1, 8);
		top.setRotationPoint(-4F, 16F, -4F);
		top.setTextureSize(64, 32);
		setRotation(top, 0F, 0F, 0F);
		
		rightSide = new ModelRenderer(this, 18, 0);
		rightSide.addBox(0F, 0F, 0F, 1, 6, 8);
		rightSide.setRotationPoint(3F, 17F, -4F);
		rightSide.setTextureSize(64, 32);
		setRotation(rightSide, 0F, 0F, 0F);
		
		leftSide = new ModelRenderer(this, 0, 0);
		leftSide.addBox(0F, 0F, 0F, 1, 6, 8);
		leftSide.setRotationPoint(-4F, 17F, -4F);
		leftSide.setTextureSize(64, 32);
		setRotation(leftSide, 0F, 0F, 0F);
		
		back = new ModelRenderer(this, 0, 16);
		back.addBox(0F, 0F, 0F, 6, 6, 1);
		back.setRotationPoint(-3F, 17F, 3F);
		back.setTextureSize(64, 32);
		setRotation(back, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		System.out.println("Render called");
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		top.render(f5);
		rightSide.render(f5);
		leftSide.render(f5);
		back.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
