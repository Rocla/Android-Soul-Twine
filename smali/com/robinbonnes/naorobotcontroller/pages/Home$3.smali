.class Lcom/robinbonnes/naorobotcontroller/pages/Home$3;
.super Ljava/lang/Object;
.source "Home.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/robinbonnes/naorobotcontroller/pages/Home;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/robinbonnes/naorobotcontroller/pages/Home;


# direct methods
.method constructor <init>(Lcom/robinbonnes/naorobotcontroller/pages/Home;)V
    .locals 0

    .prologue
    .line 1
    iput-object p1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Home$3;->this$0:Lcom/robinbonnes/naorobotcontroller/pages/Home;

    .line 109
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3
    .param p1, "arg0"    # Landroid/view/View;

    .prologue
    .line 113
    iget-object v2, p0, Lcom/robinbonnes/naorobotcontroller/pages/Home$3;->this$0:Lcom/robinbonnes/naorobotcontroller/pages/Home;

    invoke-virtual {v2}, Lcom/robinbonnes/naorobotcontroller/pages/Home;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    .line 114
    .local v0, "context":Landroid/content/Context;
    new-instance v1, Landroid/content/Intent;

    const-class v2, Lcom/robinbonnes/naorobotcontroller/pages/Options;

    invoke-direct {v1, v0, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 115
    .local v1, "intent":Landroid/content/Intent;
    iget-object v2, p0, Lcom/robinbonnes/naorobotcontroller/pages/Home$3;->this$0:Lcom/robinbonnes/naorobotcontroller/pages/Home;

    invoke-virtual {v2, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Home;->startActivity(Landroid/content/Intent;)V

    .line 116
    return-void
.end method
