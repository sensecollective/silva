package ch.bildspur.silva

import ch.bildspur.silva.configuration.ConfigurationController
import ch.bildspur.silva.model.AppConfig
import ch.bildspur.silva.model.Leaf
import ch.bildspur.silva.view.UIController
import processing.core.PApplet
import processing.core.PConstants
import processing.core.PVector


/**
 * Created by cansik on 04.02.17.
 */
class Sketch : PApplet() {
    companion object {
        @JvmStatic
        val WINDOW_WIDTH = 800
        @JvmStatic
        val WINDOW_HEIGHT = 1024

        @JvmStatic
        val NAME = "Silva Manager"

        @JvmStatic
        val URI_NAME = "silva"

        @JvmStatic
        val VERSION = "0.1"

        @JvmStatic
        lateinit var instance: Sketch

        @JvmStatic
        fun map(value: Double, start1: Double, stop1: Double, start2: Double, stop2: Double): Double {
            return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1))
        }
    }

    private val ui = UIController(this)

    private val config = ConfigurationController()

    var appConfig = AppConfig()

    override fun settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT, PConstants.P2D)
        pixelDensity = 2

        instance = this
    }

    override fun setup() {
        surface.setTitle("$NAME - $VERSION")
        surface.setIcon(loadImage("images/silva-logo-128.png"))

        appConfig = config.loadAppConfig()

        /*
        // add leafes
        (0 until appConfig.defaultLeafCount).forEach {
            val leaf = Leaf(it)
            leaf.position.target = PVector.random2D().mult(random(50f, 300f))
            appConfig.leafs.add(leaf)
        }

        config.saveAppConfig(appConfig)
        */

        ui.setup(g, appConfig)
    }

    override fun draw() {
        background(5)
        ui.render()
    }

    fun run()
    {
        this.runSketch()
    }

    override fun mouseDragged() {
        ui.mouseDragged(PVector(mouseX.toFloat(), mouseY.toFloat()))
    }

    override fun mousePressed() {
        ui.mousePressed(PVector(mouseX.toFloat(), mouseY.toFloat()))
    }

    override fun mouseMoved() {
        ui.mouseMoved(PVector(mouseX.toFloat(), mouseY.toFloat()))
    }

    override fun mouseReleased() {
        ui.mouseReleased(PVector(mouseX.toFloat(), mouseY.toFloat()))
    }
}