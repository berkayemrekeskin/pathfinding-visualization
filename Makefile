# Directories
SRC_DIR = src
OUT_DIR = out

# Find all .java files
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Create .class files list by replacing .java with .class and src with out
CLASSES := $(patsubst $(SRC_DIR)/%.java,$(OUT_DIR)/%.class,$(SOURCES))

# Compiler
JAVAC = javac

# JVM
JAVA = java

# Main class
MAIN_CLASS = src.Main

# Target to compile the project
all: $(CLASSES)

# Rule to compile .java files to .class files in the out directory
$(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(OUT_DIR) $<

# Target to run the main class
run: all
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS)

# Target to clean the out directory
clean:
	rm -rf $(OUT_DIR)/*

.PHONY: all run clean
