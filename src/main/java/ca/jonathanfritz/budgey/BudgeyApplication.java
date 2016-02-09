package ca.jonathanfritz.budgey;

import ca.jonathanfritz.budgey.commands.ImportCommand;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BudgeyApplication extends Application<BudgeyConfiguration> {

	@Override
	public void run(final BudgeyConfiguration configuration, final Environment environment) throws Exception {

	}

	@Override
	public void initialize(final Bootstrap<BudgeyConfiguration> bootstrap) {
		bootstrap.addCommand(new ImportCommand());
	}

	public static void main(final String[] args) throws Exception {
		new BudgeyApplication().run(args);
	}

}
