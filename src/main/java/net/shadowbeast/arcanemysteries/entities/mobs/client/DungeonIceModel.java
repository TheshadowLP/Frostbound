package net.shadowbeast.arcanemysteries.entities.mobs.client;
// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
public class DungeonIceModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart main;
	public DungeonIceModel(ModelPart root) {
		this.main = root.getChild("main");
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_leg = main.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(27, 90).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, -22.0F, 2.5F));

		PartDefinition right_lower = right_leg.addOrReplaceChild("right_lower", CubeListBuilder.create().texOffs(79, 84).addBox(-4.5F, 0.0F, -2.0F, 9.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -2.5F));

		PartDefinition right_foot = right_lower.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(94, 17).addBox(-4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 2.0F));

		PartDefinition right_finger3 = right_foot.addOrReplaceChild("right_finger3", CubeListBuilder.create().texOffs(112, 43).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, -3.0F));

		PartDefinition right_finger2 = right_foot.addOrReplaceChild("right_finger2", CubeListBuilder.create().texOffs(50, 112).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -3.0F));

		PartDefinition right_finger1 = right_foot.addOrReplaceChild("right_finger1", CubeListBuilder.create().texOffs(112, 37).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, -3.0F));

		PartDefinition left_leg = main.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(77, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -22.0F, 2.5F));

		PartDefinition left_lower = left_leg.addOrReplaceChild("left_lower", CubeListBuilder.create().texOffs(0, 79).addBox(-4.5F, 0.0F, -2.0F, 9.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -2.5F));

		PartDefinition left_foot = left_lower.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(78, 74).addBox(-4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 2.0F));

		PartDefinition left_finger1 = left_foot.addOrReplaceChild("left_finger1", CubeListBuilder.create().texOffs(112, 0).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, -3.0F));

		PartDefinition left_finger2 = left_foot.addOrReplaceChild("left_finger2", CubeListBuilder.create().texOffs(40, 107).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -3.0F));

		PartDefinition left_finger3 = left_foot.addOrReplaceChild("left_finger3", CubeListBuilder.create().texOffs(103, 110).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, -3.0F));

		PartDefinition chest = main.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 31).addBox(-11.0F, 0.0F, -8.0F, 22.0F, 8.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -30.0F, 3.0F));

		PartDefinition upper_chest = chest.addOrReplaceChild("upper_chest", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -14.0F, -6.0F, 30.0F, 14.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r1 = upper_chest.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(75, 31).addBox(0.5F, -0.5F, 7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(60, 62).addBox(-0.5F, -1.5F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(59, 39).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 11.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = upper_chest.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(98, 102).addBox(0.5F, -0.5F, 7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(64, 112).addBox(-0.5F, -1.5F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(98, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 11.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = upper_chest.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(113, 110).addBox(0.5F, -0.5F, 7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 92).addBox(0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 113).addBox(-0.5F, -1.5F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(103, 74).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 11.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r4 = upper_chest.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(114, 71).addBox(0.5F, -0.5F, 7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(94, 26).addBox(0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(35, 113).addBox(-0.5F, -1.5F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(105, 7).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 11.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 54).addBox(-7.0F, -10.0F, -10.0F, 14.0F, 10.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(74, 49).addBox(-7.0F, -10.0F, -11.0F, 14.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -44.0F, -3.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(11, 33).addBox(5.9F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(45, 56).addBox(3.0F, -3.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(11, 56).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(79, 2).addBox(-4.0F, -3.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(55, 67).addBox(-5.9F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 16).addBox(4.9F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 16).addBox(2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 43).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 66).addBox(-4.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 86).addBox(-6.9F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.0F, 3.0543F, 0.0F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(63, 60).addBox(11.0F, -0.5F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(43, 65).addBox(8.0F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 107).addBox(4.0F, -1.5F, -2.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(59, 31).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -2.0F, 0.0F, -0.1745F, -1.5708F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(77, 4).addBox(11.0F, -0.5F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 79).addBox(8.0F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(57, 54).addBox(4.0F, -1.5F, -2.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(26, 79).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -10.0F, -2.0F, 0.0F, -0.1745F, -2.3562F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(43, 58).addBox(11.0F, -0.5F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(102, 64).addBox(8.0F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(105, 83).addBox(4.0F, -1.5F, -2.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(103, 102).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -10.0F, -2.0F, 0.0F, -0.1745F, -0.7854F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(58, 54).addBox(-7.5F, -1.1F, -9.0F, 15.0F, 6.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(73, 118).addBox(-6.5F, -11.1F, -1.0F, 13.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(60, 112).addBox(-6.5F, -11.1F, -6.0F, 0.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(60, 112).addBox(6.5F, -11.1F, -6.0F, 0.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -3.0F));

		PartDefinition cube_r9 = jaw.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(11, 33).addBox(4.9F, -2.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 16).addBox(4.9F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(45, 56).addBox(2.0F, -3.0F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 16).addBox(2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(11, 56).addBox(0.0F, -2.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(79, 2).addBox(-3.0F, -3.0F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 66).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(55, 67).addBox(-6.9F, -2.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 86).addBox(-6.9F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -7.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition bone = jaw.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(71, 60).addBox(-1.75F, -0.1F, -6.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -1.0F, -1.0F));

		PartDefinition left_arm = main.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(74, 31).addBox(0.0F, -4.5F, -4.5F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.0F, -7.5F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(113, 89).addBox(6.5F, -10.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 79).addBox(7.0F, -13.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.5F, -15.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(15.0F, -37.5F, 2.5F));

		PartDefinition left_arm_lower = left_arm.addOrReplaceChild("left_arm_lower", CubeListBuilder.create().texOffs(102, 49).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 4.5F, 0.0F));

		PartDefinition left_arm_more_lower = left_arm_lower.addOrReplaceChild("left_arm_more_lower", CubeListBuilder.create().texOffs(0, 97).addBox(-3.0F, 0.0F, -3.5F, 6.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition hand = left_arm_more_lower.addOrReplaceChild("hand", CubeListBuilder.create().texOffs(103, 26).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -2.5F));

		PartDefinition left_hand_finger3 = hand.addOrReplaceChild("left_hand_finger3", CubeListBuilder.create().texOffs(116, 64).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 5.0F, 5.0F));

		PartDefinition left_hand_finger2 = hand.addOrReplaceChild("left_hand_finger2", CubeListBuilder.create().texOffs(116, 79).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 5.0F, 3.0F));

		PartDefinition left_hand_finger1 = hand.addOrReplaceChild("left_hand_finger1", CubeListBuilder.create().texOffs(101, 116).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 5.0F, 1.0F));

		PartDefinition left_thumb = hand.addOrReplaceChild("left_thumb", CubeListBuilder.create().texOffs(0, 54).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition right_arm = main.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(49, 74).addBox(-10.0F, -4.5F, -4.5F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-10.0F, -7.5F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(113, 95).addBox(-9.5F, -10.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 97).addBox(-9.0F, -13.5F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-8.5F, -15.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-15.0F, -37.5F, 2.5F));

		PartDefinition right_lower_arm = right_arm.addOrReplaceChild("right_lower_arm", CubeListBuilder.create().texOffs(81, 102).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 4.5F, 0.0F));

		PartDefinition right_more_lower_arm = right_lower_arm.addOrReplaceChild("right_more_lower_arm", CubeListBuilder.create().texOffs(55, 95).addBox(-3.0F, 0.0F, -3.5F, 6.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition right_hand = right_more_lower_arm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(43, 54).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -2.5F));

		PartDefinition right_Arm_finger3 = right_hand.addOrReplaceChild("right_Arm_finger3", CubeListBuilder.create().texOffs(115, 114).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 5.0F, 5.0F));

		PartDefinition right_arm_finger2 = right_hand.addOrReplaceChild("right_arm_finger2", CubeListBuilder.create().texOffs(10, 114).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 5.0F, 3.0F));

		PartDefinition right_arm_finger1 = right_hand.addOrReplaceChild("right_arm_finger1", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 5.0F, 1.0F));

		PartDefinition right_thumb = right_hand.addOrReplaceChild("right_thumb", CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	@Override
	public @NotNull ModelPart root() {
		return main;
	}
}