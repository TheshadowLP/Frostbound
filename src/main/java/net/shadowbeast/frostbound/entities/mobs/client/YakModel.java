package net.shadowbeast.frostbound.entities.mobs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class YakModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public YakModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -26.0F, -16.0F, 12.0F, 15.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-5.0F, -25.0F, -2.0F, 10.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 4.0F));

        PartDefinition back_right_leg = body.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(0, 55).addBox(-6.0F, -22.0F, 1.0F, 4.0F, 17.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(-5.5F, -10.0F, 4.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_left_leg = body.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(46, 36).addBox(2.0F, -22.0F, 1.0F, 4.0F, 17.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(38, 0).addBox(2.5F, -10.0F, 4.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_left_leg = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(26, 55).addBox(3.0F, -22.0F, -13.0F, 4.0F, 17.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(3.5F, -10.0F, -11.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_right_leg = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(0, 29).addBox(-6.5F, -10.0F, -11.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 62).addBox(-7.0F, -22.0F, -13.0F, 4.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(72, 36).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.0F, 11.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(52, 0).addBox(-4.0F, -2.4624F, -2.5518F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(42, 19).addBox(-4.0F, -2.4624F, 1.4482F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(68, 16).addBox(-3.0F, 4.5376F, -3.5518F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(33, 38).addBox(-7.0F, -1.4624F, -1.5518F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 34).addBox(4.0F, -1.4624F, -1.5518F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(74, 70).addBox(7.0F, -1.4624F, -6.5518F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(72, 53).addBox(-9.0F, -1.4624F, -6.5518F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -23.0F, -20.0F, -0.5672F, 0.0F, 0.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(66, 62).addBox(-3.5F, -2.1492F, 3.3736F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.6868F, -1.9254F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
