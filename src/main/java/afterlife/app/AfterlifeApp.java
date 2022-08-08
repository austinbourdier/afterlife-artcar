/**
 * Copyright 2020- Mark C. Slee, Heron Arts LLC
 *
 * This file is part of the LX Studio software library. By using
 * LX, you agree to the terms of the LX Studio Software License
 * and Distribution Agreement, available at: http://lx.studio/license
 *
 * Please note that the LX license is not open-source. The license
 * allows for free, non-commercial use.
 *
 * HERON ARTS MAKES NO WARRANTY, EXPRESS, IMPLIED, STATUTORY, OR
 * OTHERWISE, AND SPECIFICALLY DISCLAIMS ANY WARRANTY OF
 * MERCHANTABILITY, NON-INFRINGEMENT, OR FITNESS FOR A PARTICULAR
 * PURPOSE, WITH RESPECT TO THE SOFTWARE.
 *
 * @author Mark C. Slee <mark@heronarts.com>
 */

package afterlife.app;

import afterlife.midi.AfterlifeMidi;
import heronarts.lx.LX;
import heronarts.lx.LXPlugin;
import heronarts.lx.studio.LXStudio;
import processing.core.PApplet;
import afterlife.pattern.*;

public class AfterlifeApp extends PApplet implements LXPlugin  {
//  private AfterlifeWholeModel model;

  private static int WIDTH = 1280;
  private static int HEIGHT = 800;

  private AfterlifeMidi midi;
  private static boolean FULLSCREEN = false;
  private static String resourceSubdir;

  @Override
  public void settings() {
    if (FULLSCREEN) {
      fullScreen(PApplet.P3D);
    } else {
      size(WIDTH, HEIGHT, PApplet.P3D);
    }
    pixelDensity(displayDensity());
  }

  @Override
  public void setup() {
    LXStudio.Flags flags = new LXStudio.Flags(this);
    background(255);
    new LXStudio(this, flags);
    this.surface.setTitle("Afterlife");
  }

  @Override
  public void initialize(LX lx) {
    // Here is where you should register any custom components or make modifications
    // to the LX engine or hierarchy. This is also used in headless mode, so note that
    // you cannot assume you are working with an LXStudio class or that any UI will be
    // available.

    // TEArtNetOutput.activateAll(lx, this.model.gapPoint.index);

//    // Register custom pattern and effect types
    lx.registry.addPattern(IsolationPattern.class);
    lx.registry.addPattern(HeavenPattern.class);
    lx.registry.addPattern(AlmostHome.class);
    lx.registry.addPattern(RainbowRoad.class);
    lx.registry.addPattern(RandomCloudColors.class);
    lx.registry.addPattern(RandomCloudColorsAlternate.class);
    lx.registry.addPattern(RandomCloudColorsLevels.class);
    lx.registry.addPattern(RandomChangePixel.class);

    this.midi = new AfterlifeMidi(lx);

  }

  public void initializeUI(LXStudio lx, LXStudio.UI ui) {
    // Here is where you may modify the initial settings of the UI before it is fully
    // built. Note that this will not be called in headless mode. Anything required
    // for headless mode should go in the raw initialize method above.
  }

  public void onUIReady(LXStudio lx, LXStudio.UI ui) {
    // At this point, the LX Studio application UI has been built. You may now add
    // additional views and components to the Ui heirarchy.

    // TEVirtualOverlays visual = new TEVirtualOverlays(this.model);
    // lx.ui.preview.addComponent(visual);
    // new TEUIControls(ui, visual, ui.leftPane.global.getContentWidth()).addToContainer(ui.leftPane.global);
    //
    // GigglePixelUI gpui = new GigglePixelUI(ui, ui.leftPane.global.getContentWidth(),
    //         this.gpListener, this.gpBroadcaster);
    // gpui.addToContainer(ui.leftPane.global);
  }

  @Override
  public void draw() {
    // All handled by core LX engine, do not modify, method exists only so that Processing
    // will run a draw-loop.
  }

  /**
   * Main interface into the program. Two modes are supported, if the --headless
   * flag is supplied then a raw CLI version of LX is used. If not, then we embed
   * in a Processing 4 applet and run as such.
   *
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    LX.log("Initializing LX version " + LXStudio.VERSION);

    PApplet.main("afterlife.app.AfterlifeApp", args);
  }

}
