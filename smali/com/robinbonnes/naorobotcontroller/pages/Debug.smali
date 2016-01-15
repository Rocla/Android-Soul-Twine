.class public Lcom/robinbonnes/naorobotcontroller/pages/Debug;
.super Landroid/app/Activity;
.source "Debug.java"


# instance fields
.field cancelbutton:Landroid/widget/Button;

.field connectedflag:Z

.field filter:Landroid/content/IntentFilter;

.field homebutton:Landroid/widget/ImageButton;

.field ip:Ljava/lang/String;

.field letop:Landroid/widget/TextView;

.field letopbutton:Landroid/widget/Button;

.field letopview:Landroid/widget/LinearLayout;

.field optionsbutton:Landroid/widget/ImageButton;

.field poort:Ljava/lang/String;

.field t1:Landroid/widget/TextView;

.field webview:Landroid/webkit/WebView;

.field private final wifiReceiver:Landroid/content/BroadcastReceiver;

.field wifiaan:Landroid/net/wifi/WifiManager;

.field wifibutton:Landroid/widget/Spinner;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 40
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    .line 51
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->connectedflag:Z

    .line 135
    new-instance v0, Lcom/robinbonnes/naorobotcontroller/pages/Debug$1;

    invoke-direct {v0, p0}, Lcom/robinbonnes/naorobotcontroller/pages/Debug$1;-><init>(Lcom/robinbonnes/naorobotcontroller/pages/Debug;)V

    iput-object v0, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->wifiReceiver:Landroid/content/BroadcastReceiver;

    .line 40
    return-void
.end method


# virtual methods
.method public heeftWifi(Z)V
    .locals 5
    .param p1, "aan"    # Z

    .prologue
    const/16 v2, 0x8

    const/4 v4, 0x0

    .line 165
    if-eqz p1, :cond_0

    .line 167
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letop:Landroid/widget/TextView;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setVisibility(I)V

    .line 168
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letopbutton:Landroid/widget/Button;

    invoke-virtual {v1, v2}, Landroid/widget/Button;->setVisibility(I)V

    .line 169
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letopview:Landroid/widget/LinearLayout;

    invoke-virtual {v1, v2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 178
    :goto_0
    return-void

    .line 171
    :cond_0
    new-instance v0, Landroid/text/SpannableString;

    const-string v1, "ALERT: WiFi has been disabled, please connect to WiFi to control the robot!"

    invoke-direct {v0, v1}, Landroid/text/SpannableString;-><init>(Ljava/lang/CharSequence;)V

    .line 172
    .local v0, "regel":Landroid/text/Spannable;
    new-instance v1, Landroid/text/style/ForegroundColorSpan;

    const/high16 v2, -0x10000

    invoke-direct {v1, v2}, Landroid/text/style/ForegroundColorSpan;-><init>(I)V

    const/4 v2, 0x7

    const/16 v3, 0x21

    invoke-interface {v0, v1, v4, v2, v3}, Landroid/text/Spannable;->setSpan(Ljava/lang/Object;III)V

    .line 173
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letop:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 174
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letop:Landroid/widget/TextView;

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->setVisibility(I)V

    .line 175
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letopbutton:Landroid/widget/Button;

    invoke-virtual {v1, v4}, Landroid/widget/Button;->setVisibility(I)V

    .line 176
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letopview:Landroid/widget/LinearLayout;

    invoke-virtual {v1, v4}, Landroid/widget/LinearLayout;->setVisibility(I)V

    goto :goto_0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 5
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    const/4 v3, 0x1

    .line 57
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 60
    const v1, 0x7f030007

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->setContentView(I)V

    .line 63
    invoke-virtual {p0}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->getWindow()Landroid/view/Window;

    move-result-object v1

    const/4 v2, 0x3

    invoke-virtual {v1, v2}, Landroid/view/Window;->setSoftInputMode(I)V

    .line 66
    const v1, 0x7f06000a

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/Button;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->cancelbutton:Landroid/widget/Button;

    .line 67
    const v1, 0x7f060003

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/ImageButton;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->homebutton:Landroid/widget/ImageButton;

    .line 68
    const v1, 0x7f060006

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/ImageButton;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->optionsbutton:Landroid/widget/ImageButton;

    .line 69
    const v1, 0x7f06000c

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letop:Landroid/widget/TextView;

    .line 70
    const v1, 0x7f06000d

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/Button;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letopbutton:Landroid/widget/Button;

    .line 71
    const-string v1, "wifi"

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/net/wifi/WifiManager;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->wifiaan:Landroid/net/wifi/WifiManager;

    .line 72
    const v1, 0x7f06000b

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/LinearLayout;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->letopview:Landroid/widget/LinearLayout;

    .line 75
    new-instance v1, Landroid/content/IntentFilter;

    invoke-direct {v1}, Landroid/content/IntentFilter;-><init>()V

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->filter:Landroid/content/IntentFilter;

    .line 76
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->filter:Landroid/content/IntentFilter;

    const-string v2, "android.net.wifi.SCAN_RESULTS"

    invoke-virtual {v1, v2}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 77
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->filter:Landroid/content/IntentFilter;

    const-string v2, "android.net.wifi.STATE_CHANGE"

    invoke-virtual {v1, v2}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 78
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->wifiReceiver:Landroid/content/BroadcastReceiver;

    iget-object v2, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->filter:Landroid/content/IntentFilter;

    invoke-virtual {p0, v1, v2}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 81
    invoke-virtual {p0}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    const-string v2, "fonts/Funkydori.otf"

    invoke-static {v1, v2}, Landroid/graphics/Typeface;->createFromAsset(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object v0

    .line 82
    .local v0, "type":Landroid/graphics/Typeface;
    const v1, 0x7f060005

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->t1:Landroid/widget/TextView;

    .line 83
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->t1:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 84
    const v1, 0x7f060004

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->t1:Landroid/widget/TextView;

    .line 85
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->t1:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    .line 88
    const-string v1, "wifi"

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/net/wifi/WifiManager;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->wifiaan:Landroid/net/wifi/WifiManager;

    .line 91
    const v1, 0x7f060029

    invoke-virtual {p0, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/webkit/WebView;

    iput-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->webview:Landroid/webkit/WebView;

    .line 92
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->webview:Landroid/webkit/WebView;

    invoke-virtual {v1}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v1

    invoke-virtual {v1, v3}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 93
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->webview:Landroid/webkit/WebView;

    invoke-virtual {v1}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v1

    invoke-virtual {v1, v3}, Landroid/webkit/WebSettings;->setPluginsEnabled(Z)V

    .line 94
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->webview:Landroid/webkit/WebView;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/webkit/WebView;->setBackgroundColor(I)V

    .line 95
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->webview:Landroid/webkit/WebView;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "http://"

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string v3, "IP"

    const/4 v4, 0x0

    invoke-static {p0, v3, v4}, Lcom/robinbonnes/naorobotcontroller/helpers/PreferenceConnector;->readString(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 98
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->homebutton:Landroid/widget/ImageButton;

    new-instance v2, Lcom/robinbonnes/naorobotcontroller/pages/Debug$2;

    invoke-direct {v2, p0}, Lcom/robinbonnes/naorobotcontroller/pages/Debug$2;-><init>(Lcom/robinbonnes/naorobotcontroller/pages/Debug;)V

    invoke-virtual {v1, v2}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 109
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->optionsbutton:Landroid/widget/ImageButton;

    new-instance v2, Lcom/robinbonnes/naorobotcontroller/pages/Debug$3;

    invoke-direct {v2, p0}, Lcom/robinbonnes/naorobotcontroller/pages/Debug$3;-><init>(Lcom/robinbonnes/naorobotcontroller/pages/Debug;)V

    invoke-virtual {v1, v2}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 120
    iget-object v1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->cancelbutton:Landroid/widget/Button;

    new-instance v2, Lcom/robinbonnes/naorobotcontroller/pages/Debug$4;

    invoke-direct {v2, p0}, Lcom/robinbonnes/naorobotcontroller/pages/Debug$4;-><init>(Lcom/robinbonnes/naorobotcontroller/pages/Debug;)V

    invoke-virtual {v1, v2}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 130
    return-void
.end method
