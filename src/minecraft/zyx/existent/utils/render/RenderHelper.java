package zyx.existent.utils.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class RenderHelper
{

  private static final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

  public static final ScaledResolution getScaledRes()
  {
    ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
    return scaledRes;
  }

  public static void drawHollowRect(float posX, float posY, float posX2, float posY2, float width, int color, boolean center)
  {
    float corners = width / 2.0F;
    float side = width / 2.0F;
    if (center)
    {
      drawRect(posX - side, posY - corners, posX + side, posY2 + corners, color);
      drawRect(posX2 - side, posY - corners, posX2 + side, posY2 + corners, color);
      drawRect(posX - corners, posY - side, posX2 + corners, posY + side, color);
      drawRect(posX - corners, posY2 - side, posX2 + corners, posY2 + side, color);
    }
    else
    {
      drawRect(posX - width, posY - corners, posX, posY2 + corners, color);
      drawRect(posX2, posY - corners, posX2 + width, posY2 + corners, color);
      drawRect(posX - corners, posY - width, posX2 + corners, posY, color);
      drawRect(posX - corners, posY2, posX2 + corners, posY2 + width, color);
    }
  }

  public static void drawGradientBorderedRect(float posX, float posY, float posX2, float posY2, float width, int color, int startColor, int endColor, boolean center)
  {
    drawGradientRect(posX, posY, posX2, posY2, startColor, endColor);
    drawHollowRect(posX, posY, posX2, posY2, width, color, center);
  }

  public static void drawCoolLines(AxisAlignedBB mask)
  {
    GL11.glPushMatrix();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glPopMatrix();
  }

  public static void drawBorderedRect(float x, float y, float x2, float y2, float l1, int col1, int col2)
  {
    drawRect(x, y, x2, y2, col2);
    float f = (col1 >> 24 & 0xFF) / 255.0F;
    float f1 = (col1 >> 16 & 0xFF) / 255.0F;
    float f2 = (col1 >> 8 & 0xFF) / 255.0F;
    float f3 = (col1 & 0xFF) / 255.0F;
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glPushMatrix();
    GL11.glColor4f(f1, f2, f3, f);
    GL11.glLineWidth(l1);
    GL11.glBegin(1);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x, y2);
    GL11.glVertex2d(x2, y2);
    GL11.glVertex2d(x2, y);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x2, y);
    GL11.glVertex2d(x, y2);
    GL11.glVertex2d(x2, y2);
    GL11.glEnd();
    GL11.glPopMatrix();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glDisable(2848);
  }

  public static void drawBorderedCorneredRect(float x, float y, float x2, float y2, float lineWidth, int lineColor, int bgColor)
  {
    drawRect(x, y, x2, y2, bgColor);
    float f = (lineColor >> 24 & 0xFF) / 255.0F;
    float f1 = (lineColor >> 16 & 0xFF) / 255.0F;
    float f2 = (lineColor >> 8 & 0xFF) / 255.0F;
    float f3 = (lineColor & 0xFF) / 255.0F;
    GL11.glEnable(3042);
    GL11.glEnable(3553);
    drawRect(x - 1.0F, y, x2 + 1.0F, y - lineWidth, lineColor);
    drawRect(x, y, x - lineWidth, y2, lineColor);
    drawRect(x - 1.0F, y2, x2 + 1.0F, y2 + lineWidth, lineColor);
    drawRect(x2, y, x2 + lineWidth, y2, lineColor);
    GL11.glDisable(3553);
    GL11.glDisable(3042);
  }

  public static double interp(double from, double to, double pct)
  {
    return from + (to - from) * pct;
  }

  public static double interpPlayerX()
  {
    return interp(Minecraft.getMinecraft().thePlayer.lastTickPosX, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().timer.renderPartialTicks);
  }

  public static double interpPlayerY()
  {
    return interp(Minecraft.getMinecraft().thePlayer.lastTickPosY, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().timer.renderPartialTicks);
  }

  public static double interpPlayerZ()
  {
    return interp(Minecraft.getMinecraft().thePlayer.lastTickPosZ, Minecraft.getMinecraft().thePlayer.posZ, Minecraft.getMinecraft().timer.renderPartialTicks);
  }

  public static void glColor(Color color)
  {
    GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
  }

  public static void glColor(int hex)
  {
    float alpha = (hex >> 24 & 0xFF) / 255.0F;
    float red = (hex >> 16 & 0xFF) / 255.0F;
    float green = (hex >> 8 & 0xFF) / 255.0F;
    float blue = (hex & 0xFF) / 255.0F;
    GL11.glColor4f(red, green, blue, alpha);
  }


  public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor)
  {
	  GL11.glEnable(3042);
	  GL11.glDisable(3553);
	  GL11.glBlendFunc(770, 771);
	  GL11.glEnable(2848);
	  GL11.glPushMatrix();
	  GL11.glShadeModel(7425);
	  GL11.glBegin(7);
	  glColor(topColor);
	  GL11.glVertex2f(x, y1);
	  GL11.glVertex2f(x1, y1);
	  glColor(bottomColor);
	  GL11.glVertex2f(x1, y);
	  GL11.glVertex2f(x, y);
	  GL11.glEnd();
	  GL11.glShadeModel(7424);
	  GL11.glPopMatrix();
	  GL11.glEnable(3553);
	  GL11.glDisable(3042);
	  GL11.glDisable(2848);
  }

  public static void drawCircle(double d, double e, double r, int c) {
      float f = ((c >> 24) & 0xff) / 255F;
      float f1 = ((c >> 16) & 0xff) / 255F;
      float f2 = ((c >> 8) & 0xff) / 255F;
      float f3 = (c & 0xff) / 255F;
      GL11.glPushMatrix();
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      GL11.glEnable(GL11.GL_LINE_SMOOTH);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(GL11.GL_LINE_LOOP);

      for (int i = 0; i <= 360; i++) {
          double x2 = Math.sin(((i * Math.PI) / 180)) * r;
          double y2 = Math.cos(((i * Math.PI) / 180)) * r;
          GL11.glVertex2d(d + x2, e + y2);
      }

      GL11.glEnd();
      GL11.glDisable(GL11.GL_LINE_SMOOTH);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glPopMatrix();
  }

  public static void drawCircle(double d, double e, double r, int c, int c2, int color1max) {
      float f = ((c >> 24) & 0xff) / 255F;
      float f1 = ((c >> 16) & 0xff) / 255F;
      float f2 = ((c >> 8) & 0xff) / 255F;
      float f3 = (c & 0xff) / 255F;
      float f0 = ((c2 >> 24) & 0xff) / 255F;
      float f01 = ((c2 >> 16) & 0xff) / 255F;
      float f02 = ((c2 >> 8) & 0xff) / 255F;
      float f03 = (c2 & 0xff) / 255F;
      GL11.glPushMatrix();
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      GL11.glEnable(GL11.GL_LINE_SMOOTH);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(GL11.GL_LINE_LOOP);

      for (int i = 0; i <= 360; i++) {
    	  if (i == (color1max + 1)) {
    		  GL11.glColor4f(f01, f02, f03, f0);
    	  }
          double x2 = Math.sin(((i * Math.PI) / 180)) * r;
          double y2 = Math.cos(((i * Math.PI) / 180)) * r;
          GL11.glVertex2d(d + x2, e + y2);
      }

      GL11.glEnd();
      GL11.glDisable(GL11.GL_LINE_SMOOTH);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glPopMatrix();
  }

  public static void drawTexture(ResourceLocation resourceLocation, float posX, float posY, float width, float height) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float f = (width + height) / 2.0F;
		int i = Math.round(f);
		GL11.glEnable(3042);
		Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
		GL11.glBegin(7);
		GL11.glTexCoord2d(0.0D, 0.0D);
		GL11.glVertex2d((double)posX, (double)posY);
		GL11.glTexCoord2d(0.0D, (double)(f / (float)i));
		GL11.glVertex2d((double)posX, (double)(posY + height));
		GL11.glTexCoord2d(1.0D, (double)(f / (float)i));
		GL11.glVertex2d((double)(posX + width), (double)(posY + height));
		GL11.glTexCoord2d(1.0D, 0.0D);
		GL11.glVertex2d((double)(posX + width), (double)posY);
		GL11.glEnd();
		GL11.glDisable(3042);
	}

  public static void drawFilledCircle(double x, double y, double r, int c) {
      float f = ((c >> 24) & 0xff) / 255F;
      float f1 = ((c >> 16) & 0xff) / 255F;
      float f2 = ((c >> 8) & 0xff) / 255F;
      float f3 = (c & 0xff) / 255F;
      GL11.glPushMatrix();
      GL11.glLineWidth(1F);
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      GL11.glEnable(GL11.GL_LINE_SMOOTH);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(GL11.GL_TRIANGLE_FAN);

      for (int i = 0; i <= 360; i++) {
          double x2 = Math.sin(((i * Math.PI) / 180)) * r;
          double y2 = Math.cos(((i * Math.PI) / 180)) * r;
          GL11.glVertex2d(x + x2, y + y2);
      }

      GL11.glEnd();
      GL11.glDisable(GL11.GL_LINE_SMOOTH);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glPopMatrix();
  }

  public static void drawFilledCircle(double x, double y, double r, int c, int start, int stop) {
      float f = ((c >> 24) & 0xff) / 255F;
      float f1 = ((c >> 16) & 0xff) / 255F;
      float f2 = ((c >> 8) & 0xff) / 255F;
      float f3 = (c & 0xff) / 255F;
      GL11.glPushMatrix();
      GL11.glLineWidth(1F);
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glDisable(GL11.GL_TEXTURE_2D);
      GL11.glEnable(GL11.GL_LINE_SMOOTH);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(GL11.GL_TRIANGLE_FAN);

      for (int i = start; i <= stop; i++) {
          double x2 = Math.sin(((i * Math.PI) / 180)) * r;
          double y2 = Math.cos(((i * Math.PI) / 180)) * r;
          GL11.glVertex2d(x + x2, y + y2);
      }

      GL11.glEnd();
      GL11.glDisable(GL11.GL_LINE_SMOOTH);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glPopMatrix();
  }

  public static void drawLines(AxisAlignedBB mask)
  {
    GL11.glPushMatrix();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
    GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
    GL11.glEnd();
    GL11.glBegin(2);
    GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
    GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
    GL11.glEnd();
    GL11.glPopMatrix();
  }


  public static void drawRect(float g, float h, float i, float j, int col1)
  {
    float f = (col1 >> 24 & 0xFF) / 255.0F;
    float f1 = (col1 >> 16 & 0xFF) / 255.0F;
    float f2 = (col1 >> 8 & 0xFF) / 255.0F;
    float f3 = (col1 & 0xFF) / 255.0F;
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glPushMatrix();
    GL11.glColor4f(f1, f2, f3, f);
    GL11.glBegin(7);
    GL11.glVertex2d(i, h);
    GL11.glVertex2d(g, h);
    GL11.glVertex2d(g, j);
    GL11.glVertex2d(i, j);
    GL11.glEnd();
    GL11.glPopMatrix();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glDisable(2848);
  }

  public static void drawRect(float g, float h, float i, float j)
  {
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glPushMatrix();
    GL11.glColor4f(0.3F, 0.0F, 0.5F, 1.0F);
    GL11.glBegin(7);
    GL11.glVertex2d(i, h);
    GL11.glVertex2d(g, h);
    GL11.glVertex2d(g, j);
    GL11.glVertex2d(i, j);
    GL11.glEnd();
    GL11.glPopMatrix();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glDisable(2848);
  }

	public static void drawRect(double x, double y, double d, double e, int c) {
		drawRect((float)x, (float)y, (float)d, (float)e, c);
	}
}

