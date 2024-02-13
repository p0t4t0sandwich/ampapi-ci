package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/p0t4t0sandwich/ampapi-go/modules"
)

func main() {
	// Get settings from settings.json
	settingsFile, err := os.ReadFile("./settings.json")
	if err != nil {
		fmt.Println("Error reading settings.json")
	}
	var settings Settings
	_ = json.Unmarshal(settingsFile, &settings)

	// Override settings from env
	ENV_IP_ADDRESS := os.Getenv("IP_ADDRESS")
	if ENV_IP_ADDRESS != "" {
		settings.IP_ADDRESS = ENV_IP_ADDRESS
	}
	ENV_PORT := os.Getenv("PORT")
	if ENV_PORT != "" {
		settings.PORT = ENV_PORT
	}
	ENV_AMP_API_URL := os.Getenv("AMP_API_URL")
	if ENV_AMP_API_URL != "" {
		settings.AMP_API_URL = ENV_AMP_API_URL
	}
	ENV_AMP_API_USERNAME := os.Getenv("AMP_API_USERNAME")
	if ENV_AMP_API_USERNAME != "" {
		settings.AMP_API_USERNAME = ENV_AMP_API_USERNAME
	}
	ENV_AMP_API_PASSWORD := os.Getenv("AMP_API_PASSWORD")
	if ENV_AMP_API_PASSWORD != "" {
		settings.AMP_API_PASSWORD = ENV_AMP_API_PASSWORD
	}

	api, _ := modules.NewMinecraft(settings.AMP_API_URL, settings.AMP_API_USERNAME, settings.AMP_API_PASSWORD)

	fmt.Println(api.Core.GetStatus())

	// gin.SetMode(gin.ReleaseMode)
	var router *gin.Engine = gin.Default()

	// Static files
	router.StaticFile("/openapi.json", "./openapi.json")

	// Docs
	router.GET("/docs", getRoot)
	router.GET("/", func(c *gin.Context) {
		c.Redirect(http.StatusMovedPermanently, "/docs")
	})

	fmt.Println("Started server on " + settings.IP_ADDRESS + ":" + settings.PORT)
	router.Run(settings.IP_ADDRESS + ":" + settings.PORT)
}

// -------------- Structs --------------

// Settings - Settings struct
type Settings struct {
	IP_ADDRESS       string `json:"IP_ADDRESS"`
	PORT             string `json:"PORT"`
	AMP_API_URL      string `json:"AMP_API_URL"`
	AMP_API_USERNAME string `json:"AMP_API_USERNAME"`
	AMP_API_PASSWORD string `json:"AMP_API_PASSWORD"`
}

// -------------- Handlers --------------

// Get root route
func getRoot(c *gin.Context) {
	// Read the html file
	html, err := os.ReadFile("./index.html")
	if err != nil {
		c.String(http.StatusInternalServerError, err.Error())
		return
	}

	// Return the html
	c.Data(http.StatusOK, "text/html", html)
}
