.class Lcom/robinbonnes/naorobotcontroller/pages/Debug$4;
.super Ljava/lang/Object;
.source "Debug.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/robinbonnes/naorobotcontroller/pages/Debug;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/robinbonnes/naorobotcontroller/pages/Debug;


# direct methods
.method constructor <init>(Lcom/robinbonnes/naorobotcontroller/pages/Debug;)V
    .locals 0

    .prologue
    .line 1
    iput-object p1, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug$4;->this$0:Lcom/robinbonnes/naorobotcontroller/pages/Debug;

    .line 120
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3
    .param p1, "arg0"    # Landroid/view/View;

    .prologue
    .line 124
    iget-object v2, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug$4;->this$0:Lcom/robinbonnes/naorobotcontroller/pages/Debug;

    invoke-virtual {v2}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    .line 125
    .local v0, "context":Landroid/content/Context;
    new-instance v1, Landroid/content/Intent;

    const-class v2, Lcom/robinbonnes/naorobotcontroller/Menu;

    invoke-direct {v1, v0, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 126
    .local v1, "intent":Landroid/content/Intent;
    iget-object v2, p0, Lcom/robinbonnes/naorobotcontroller/pages/Debug$4;->this$0:Lcom/robinbonnes/naorobotcontroller/pages/Debug;

    invoke-virtual {v2, v1}, Lcom/robinbonnes/naorobotcontroller/pages/Debug;->startActivity(Landroid/content/Intent;)V

    .line 127
    return-void
.end method
